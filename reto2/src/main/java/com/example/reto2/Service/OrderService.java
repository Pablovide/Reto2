package com.example.reto2.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.reto2.Repositories.Entities.OrderEntity;
import com.example.reto2.Repositories.Interfaces.OrderRepository;
import com.example.reto2.Service.Enums.OrderStatus;
import com.example.reto2.Service.Models.FullOrder;
import com.example.reto2.Service.Models.MakeOrder;
import com.example.reto2.Service.Models.OrderDTO;
import com.example.reto2.Service.Models.OrderProductDTO;
import com.example.reto2.Service.Models.ProductDTO;

import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderProductService orderProductService;

    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream()
                .map(x -> modelMapper.map(x, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public OrderDTO add(OrderDTO order) {
        OrderEntity entityToInsert = modelMapper.map(order, OrderEntity.class);
        OrderEntity result = orderRepository.save(entityToInsert);
        return modelMapper.map(result, OrderDTO.class);
    }

    public OrderDTO update(Long id, OrderDTO order) {
        OrderEntity entityToUpdate = modelMapper.map(order, OrderEntity.class);
        entityToUpdate.setId(id);
        OrderEntity result = orderRepository.save(entityToUpdate);
        return modelMapper.map(result, OrderDTO.class);
    }

    public void delete(Long id) {
        Optional<OrderEntity> entityToDelete = orderRepository.findById(id);
        if (entityToDelete.isPresent()) {
            orderRepository.delete(entityToDelete.get());
        }
    }

    public OrderDTO findById(Long id) {
        Optional<OrderEntity> entity = orderRepository.findById(id);
        if (entity.isPresent()) {
            return modelMapper.map(entity.get(), OrderDTO.class);
        } else {
            return null;
        }
    }

    public OrderDTO getCurrentOrder() {
        var orders = orderRepository.findAll();
        for (OrderEntity order : orders) {
            if (order.getStatus() == OrderStatus.PENDING.toString()) {
                return modelMapper.map(order, OrderDTO.class);
            }
        }
        return null;
    }

    public FullOrder getFullOrderByOrderId(Long id) {
        FullOrder fullOrder = new FullOrder();
        var order = orderRepository.findById(id);
        if (order == null) {
            throw new ObjectNotFoundException(id, "Order");
        }
        fullOrder.setOrder(modelMapper.map(order.get(), OrderDTO.class));
        fullOrder.setProducts(productService.getAllProductsByOrderId(id));
        return fullOrder;
    }

    public OrderDTO createOrder(MakeOrder makeOrder) {
        OrderDTO order = new OrderDTO();
        order.setCustomerName(makeOrder.getCustomerName());
        order.setCreationDate(Calendar.getInstance().getTime());
        order.setStatus(OrderStatus.PENDING);
        order = add(order);
        HashMap<Long, Integer> productHMap = mapProductListToHashMap(makeOrder.getProducts());
        for (var product : productHMap.entrySet()) {
            OrderProductDTO orderProduct = new OrderProductDTO();
            orderProduct.setOrderId(order.getId());
            orderProduct.setProductId(product.getKey());
            orderProduct.setQuantity(product.getValue());
            orderProductService.add(orderProduct);
        }
        return order;
    }

    private HashMap<Long, Integer> mapProductListToHashMap(ArrayList<ProductDTO> products) {
        HashMap<Long, Integer> productHMap = new HashMap<>();
        for (ProductDTO product : products) {
            if (productHMap.containsKey(product.getId())) {
                productHMap.put(product.getId(), productHMap.get(product.getId()) + 1);
            } else {
                productHMap.put(product.getId(), 1);
            }
        }
        return productHMap;
    }

    public void addProductToOrder(Long id, Long productId, Integer quantity) {
        var orderProducts = orderProductService.findByOrderId(id);
        if (orderProducts == null) {
            throw new ObjectNotFoundException(id, "Order");
        }
        if (productService.findById(productId) == null) {
            throw new ObjectNotFoundException(productId, "Product");
        }
        for (var orderProduct : orderProducts) {
            if (orderProduct.getProductId() == productId) {
                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
                orderProductService.update(orderProduct.getId(), orderProduct);
                return;
            }
        }
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setOrderId(id);
        orderProductDTO.setProductId(productId);
        orderProductDTO.setQuantity(quantity);
        orderProductService.add(orderProductDTO);
    }

    public void removeProductFromOrder(Long id, Long productId, Integer quantity) throws Exception {
        var orderProducts = orderProductService.findByOrderId(id);
        if (orderProducts == null) {
            throw new ObjectNotFoundException(id, "Order");
        }
        if (productService.findById(productId) == null) {
            throw new ObjectNotFoundException(productId, "Product");
        }
        for (var orderProduct : orderProducts) {
            if (orderProduct.getProductId() == productId) {
                if (orderProduct.getQuantity() - quantity < 0) {
                    throw new Exception("Quantity is not valid");
                }
                orderProduct.setQuantity(orderProduct.getQuantity() - quantity);
                orderProductService.update(orderProduct.getId(), orderProduct);
                if(orderProduct.getQuantity() == 0) {
                    orderProductService.delete(orderProduct.getId());
                }
                return;
            }
        }
        throw new Exception("Product not found");
    }

    public void deleteFullOrder(Long id) {
        delete(id);
        var orderProducts = orderProductService.findByOrderId(id);
        for (var orderProduct : orderProducts) {
            orderProductService.delete(orderProduct.getId());
        }
    }

    public void acceptOrder(Long id) {
        var order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setStatus(OrderStatus.ACCEPTED.toString());
            orderRepository.save(order.get());
        }
    }
}

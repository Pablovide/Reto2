package com.example.reto2.Web.API;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.example.reto2.Service.OrderProductService;
import com.example.reto2.Service.OrderService;
import com.example.reto2.Service.ProductService;
import com.example.reto2.Service.Enums.OrderStatus;
import com.example.reto2.Service.Models.FullOrder;
import com.example.reto2.Service.Models.MakeOrder;
import com.example.reto2.Service.Models.OrderDTO;
import com.example.reto2.Service.Models.OrderProductDTO;
import com.example.reto2.Service.Models.ProductDTO;

import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final OrderProductService orderProductService;

    public OrderController(OrderService orderService, ProductService productService,
            OrderProductService orderProductService) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderProductService = orderProductService;
    }

    @GetMapping("")
    public List<OrderDTO> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/full/{id}")
    public FullOrder getFullOrderDataByOrderId(@PathVariable Long id) throws Exception {
        FullOrder fullOrder = new FullOrder();
        var order = orderService.findById(id);
        if (order == null) {
            throw new ObjectNotFoundException(id, "Order");
        }
        fullOrder.setOrder(order);
        try {
            fullOrder.setProducts(getAllProductsByOrderId(id));
        } catch (Exception e) {
            throw new ObjectNotFoundException(id, "Products");
        }
        return fullOrder;
    }

    @PostMapping("")
    public Long createOrder(@RequestBody MakeOrder makeOrder) {
        OrderDTO order = new OrderDTO();
        order.setCustomerName(makeOrder.getCustomerName());
        order.setCreationDate(Calendar.getInstance().getTime());
        order.setStatus(OrderStatus.PENDING);
        order = orderService.add(order);
        HashMap<Long, Integer> productHMap = mapProductListToHashMap(makeOrder.getProducts());
        for (var product : productHMap.entrySet()) {
            OrderProductDTO orderProduct = new OrderProductDTO();
            orderProduct.setOrderId(order.getId());
            orderProduct.setProductId(product.getKey());
            orderProduct.setQuantity(product.getValue());
            orderProductService.add(orderProduct);
        }
        return order.getId();
    }

    @PutMapping("/add/{id}")
    public void addProductToOrder(@PathVariable Long id, @RequestParam Long productId, @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
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

    @PutMapping("/remove/{id}")
    public void removeProductFromOrder(@PathVariable Long id, @RequestParam Long productId, @RequestParam(defaultValue = "1") Integer quantity)
            throws Exception {
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
                return;
            }
        }
        throw new Exception("Product not found");
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        var orderProducts = orderProductService.findByOrderId(id);
        for (var orderProduct : orderProducts) {
            orderProductService.delete(orderProduct.getId());
        }
    }

    private Object[][] getAllProductsByOrderId(Long id) {
        var orderProducts = orderProductService.findByOrderId(id);
        var iterator = -1;
        var products = new Object[orderProducts.size()][2];
        for (OrderProductDTO orderProduct : orderProducts) {
            iterator++;
            var product = productService.findById(orderProduct.getProductId());
            if (product == null) {
                throw new ObjectNotFoundException(orderProduct.getProductId(), "Product");
            }
            products[iterator][0] = product;
            products[iterator][1] = orderProduct.getQuantity();
        }
        return products;
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
}

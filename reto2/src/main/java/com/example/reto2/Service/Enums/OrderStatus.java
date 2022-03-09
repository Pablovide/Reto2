package com.example.reto2.Service.Enums;

public enum OrderStatus {
    PENDING
    {
        @Override
        public String toString() {
            return "PENDING";
        }
    },
    ACCEPTED{
        @Override
        public String toString() {
            return "ACCEPTED";
        }
    },
    SHIPPED{
        @Override
        public String toString() {
            return "SHIPPED";
        }
    },
    DELIVERED{
        @Override
        public String toString() {
            return "DELIVERED";
        }
    },
    CANCELLED{
        @Override
        public String toString() {
            return "CANCELLED";
        }
    }
}

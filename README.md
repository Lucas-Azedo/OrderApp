# MVP - Online Ordering Application

This project aims to deliver a Minimum Viable Product (MVP) for an online ordering system. The application is divided into two main areas: **Admin** and **User**.

---

## JSON

### Request
#### Order
```json
{
  "orderItems": [
    {
      "itemId": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
      "quantity": 2
    },
    {
      "itemId": "0fedcba9-8765-4321-0fed-cba987654321",
      "quantity": 1
    }
  ],
  "customerName": "João da Silva",
  "deliveryAddress": "Rua das Flores, 123, São Paulo - SP"
}
```

#### Item
```json
{
  "name": "Hambúrguer Artesanal",
  "description": "Hambúrguer com carne 100% Angus, pão brioche e queijo cheddar",
  "price": 29.90
}

```

### Response
#### Order
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "items": [
    {
      "itemId": "a1b2c3d4-e5f6-7890-1234-56789abcdef0",
      "name": "Hambúrguer Artesanal",
      "price": 29.90,
      "quantity": 2
    },
    {
      "itemId": "0fedcba9-8765-4321-0fed-cba987654321",
      "name": "Suco Natural",
      "price": 9.50,
      "quantity": 1
    }
  ],
  "customerName": "João da Silva",
  "deliveryAddress": "Rua das Flores, 123, São Paulo - SP",
  "orderAmount": 69.30
}
```

#### Item
```json
{
  "id": "d1f80a97-8b6a-4c51-bf36-0a6f66b09d23",
  "name": "Hambúrguer Artesanal",
  "description": "Hambúrguer com carne 100% Angus, pão brioche e queijo cheddar",
  "price": 29.90
}
```

---

## Feature Checklist

### Backend
#### Admin

- [x] **Item CRUD**
  - [x] Create a new item
  - [x] List all items
  - [x] Update an existing item
  - [x] Delete an item

- [x] **Order Management**
  - [x] View all user orders
  - [x] View order details

---

#### User

- [x] **Place an Order**
  - [x] View available items
  - [x] Select one or more items
  - [x] Provide required order details (e.g., name, address, notes)
  - [x] Confirm and submit order

### Frontend
#### Admin Interface

- [ ] **Item Management**
  - [ ] Reusable form component for item creation and update
  - [ ] List view with actions (edit, delete)
  - [ ] Modal or inline editing for quick updates

- [ ] **Order Management**
  - [ ] Paginated list of all user orders
  - [ ] Expandable/clickable rows to view order details

#### User Interface

- [ ] **Product Browsing and Selection**
  - [ ] Reusable item card component to display product information
  - [ ] Grid/list layout for available items
  - [ ] Add-to-order button with quantity selector

- [ ] **Order Form**
  - [ ] Input fields for name, address, and optional notes
  - [ ] Form validation for required fields
  - [ ] Reusable form layout and submit button

- [ ] **Order Confirmation**
  - [ ] Review screen summarizing selected items and user data
  - [ ] Final confirmation action
  - [ ] Success message or redirect after submission

#### Shared Components

- [ ] Header/navigation bar (conditionally rendered for admin/user)
- [ ] Notification/toast system for feedback (success, error)
- [ ] Loading spinner and empty state components
- [ ] Button, input, and modal components reused across views

---

## Tech Stack

- Backend developed using **Java + Spring Boot**
- Use of a PostgreSQL as main relational database
- Authentication is optional for the MVP, but can be added later

---

## Next Steps (Post-MVP)

- [ ] Implement authentication for admin and users
- [ ] Enable user order history
- [ ] Add order status (in progress, completed, cancelled)
- [ ] Integrate payment methods
- [ ] Improve responsive layout and UI/UX


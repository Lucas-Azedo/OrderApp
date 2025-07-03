# MVP - Online Ordering Application

This project aims to deliver a Minimum Viable Product (MVP) for an online ordering system. The application is divided into two main areas: **Admin** and **User**.

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

- [ ] **Place an Order**
  - [ ] View available items
  - [ ] Select one or more items
  - [ ] Provide required order details (e.g., name, address, notes)
  - [ ] Confirm and submit order

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


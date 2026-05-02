# RecoverEase - Lost & Found System
## Java OOP Project | End Semester

---

## How to Run

```bash
# From the RecoverEase/ folder:
mkdir -p out data images
javac -sourcepath src -d out src/Main.java
java -cp out Main
```

Or just run:  `./run.sh`

---

## Default Login (Institute Head)
| Email                   | Password  |
|-------------------------|-----------|
| admin@recoverease.in    | Admin@123 |

Created automatically on first run.

---

## Project Structure

```
RecoverEase/
├── src/
│   ├── Main.java                  ← Entry point (login / signup)
│   ├── model/
│   │   ├── User.java              ← User with Role enum (USER / INSTITUTE_HEAD)
│   │   ├── Item.java              ← Abstract base class
│   │   ├── LostItem.java          ← Extends Item
│   │   └── FoundItem.java         ← Extends Item (tracks handover status)
│   ├── service/
│   │   ├── AuthService.java       ← Login, signup, delete user
│   │   └── ReportManager.java     ← Post, edit, delete, search, match items
│   ├── ui/
│   │   ├── UserMenu.java          ← Regular user terminal UI
│   │   └── AdminMenu.java         ← Institute Head panel
│   └── util/
│       ├── FileHandler.java       ← Read/write users.txt and items.txt + image copy
│       └── IDGenerator.java       ← UUID-based short IDs
├── data/
│   ├── users.txt                  ← All login credentials (plain text, CSV)
│   └── items.txt                  ← All lost/found item records
├── images/                        ← Uploaded item images copied here
└── run.sh                         ← Compile + run script
```

---

## OOP Concepts Used

| Concept         | Where                                                         |
|-----------------|---------------------------------------------------------------|
| Abstraction     | `Item` is abstract — cannot be instantiated directly          |
| Inheritance     | `LostItem` and `FoundItem` both extend `Item`                 |
| Encapsulation   | All fields private, accessed only via getters/setters         |
| Polymorphism    | `getType()` returns "LOST" or "FOUND" — used in file storage  |
| File Handling   | users.txt (login data), items.txt (reports), images/ (photos) |

---

## Role Hierarchy

```
INSTITUTE_HEAD  →  Full control: view all, delete any post, delete user accounts
      ↑
    USER         →  Post lost/found, edit/delete own posts, search, view matches
```

---

## File Storage Format

**users.txt** (one user per line):
```
name,email,password,ROLE
Alice,alice@example.com,pass123,USER
Admin,admin@recoverease.in,Admin@123,INSTITUTE_HEAD
```

**items.txt** (one item per line):
```
TYPE|ID|title|description|location|date|imagePath|postedBy
LOST|A1B2C3D4|Wallet|Brown leather wallet|Main Gate|2024-01-15|images/A1B2C3D4.jpg|alice@example.com
FOUND|E5F6G7H8|Keys|Honda keys with tag|Library|2024-01-16||bob@example.com
```

---

## Features

### Regular User
- Sign up / Login
- Report lost item (with optional image)
- Report found item (with optional image)
- View all lost / found items
- View own posts
- Edit own posts
- Delete own posts
- Search by keyword
- Find potential matches for a lost item

### Institute Head (Admin)
- Everything a user can do
- View ALL items together
- Delete ANY item by ID
- Edit ANY item by ID
- View list of all registered users
- **Delete any user account from users.txt**

# bookStore

## The task
You are going to build an API for a bookstore. You should be able to list the books available and buy them if they are in stock. Then add functionality as time allow. There are some suggestions below, but if you come up with things you think fit the domain and that show special knowledge, that's brilliant! The task is loosely defined with the intention of giving a picture of how you would approach such a task. Think that you are building it as a product that can be built upon to extend the functionality and later release it. Be careful not to get stuck. It is better to finish some parts and leave other parts unfinished, than to get stuck on a specific part.
Feel free to come back with any questions!
Suggested functionality:
● Database connection (instead of mocking)
● Tests
● Filtration
● Validation
● Relationships
● Authenticated admin endpoint
● Storage of images on covers

## Implementation
- Spring Boot REST API
- List all books (`GET /api/books`)
- Buy a book if in stock (`POST /api/books/{bookId}/buy?quantity=X`)
- H2 in-memory database (preloaded with sample novel books)
- Entity-DTO separation
    - **Entities**: `BookEntity`, `OrderEntity` → used for persistence
    - **DTOs**: `Book`, `Order` → used for API responses
- Preloaded book data at application startup using `CommandLineRunner`
- The API uses a global exception handler to return consistent JSON error responses.
- The API uses **Basic Authentication**.
- `/api/admin/**` → Requires `ADMIN` role
- **Admin credentials**: `admin:password`
- Added test cases for controller and service layers.

## API :
### for all Users :
- List All book : (Get) /api/books
- Buy Book : (Post) /api/books/{book_id}/buy?quantity={quantity}

### For admin User :
- Sample request body to add and update book :
                    {
                      "title": "Pride and Prejudice",
                      "author": "Jane Austen",
                      "price": 10.99,
                      "stock": 10
                    }

- Add book : (Post) /api/admin/books
- Update Book : (Put) /api/admin/books/{book_id}
- Delete Book : (Delete) api/admin/books/{book_id}

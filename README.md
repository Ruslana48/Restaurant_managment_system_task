## Restaurant_managment_system
>###### Final project for EPAM Java external courses


![зображення](https://user-images.githubusercontent.com/85941909/206445186-5708a50e-992b-4a0d-897e-b10e48fce7da.png)
![зображення](https://user-images.githubusercontent.com/85941909/206445361-cc1ad514-bd82-4502-a1be-42b84c56228a.png)
![зображення](https://user-images.githubusercontent.com/85941909/206445897-79381cbb-8827-4ca3-b1e4-f1409ef29ee0.png)

## User roles: (Client, Manager)

   ###### Client can view catalog of dishes with paginating, sorting and filtering, add them to cart, change their count or delete them from cart. Can make an order and observe status of order.
   ###### Manager can view all orders with filtering, change their status. Can delete a user or change his role (manager/client).
   
   
   
## Database schema

![зображення](https://user-images.githubusercontent.com/85941909/206448501-1467e4af-e092-446e-a3f4-9e961a505d29.png)


## Used technologies


    Java EE
        Servlets
        Filters
        Listeners
        JSP
    MySql (used standart JDBC for connection)
    
## Used patterns

    MVC
    DAO
    Abstract factory for dao for different databases
    Singleton for ConnectionPool
    Builder
    
    
## Set up project

    Clone current repository
    Set your database connect rules in restaurant/src/main/webapp/META-INF/context.xml
    Run restaurant/sql/db-create.sql to set up database on your devise
    Run app using servlet container (Recommended Tomcat v.9.0.52)
    Use app in your browser
    
 ## Screenshots
 
 ## Log in/Sign Up 
 
 ![зображення](https://user-images.githubusercontent.com/85941909/206449963-fba1c579-74b8-4603-92ef-9c223fb7e5e8.png)
 ![зображення](https://user-images.githubusercontent.com/85941909/206451608-067b48ae-5383-420c-b1bd-3fdb5eed474f.png)

## Fields validation

![зображення](https://user-images.githubusercontent.com/85941909/206450077-f2cb73a9-cbd5-4bab-a92b-7948b3226d2a.png)

## Localization

![зображення](https://user-images.githubusercontent.com/85941909/206450171-4dd83a86-16bc-43d0-9215-f5866392218d.png)


   ## For User
 
![зображення](https://user-images.githubusercontent.com/85941909/209664832-eb70ff9e-e95e-4668-a43d-3056592f11c4.png)
![зображення](https://user-images.githubusercontent.com/85941909/206450470-41c83f9e-8241-440e-84e3-0eb5a4e006bd.png)
![зображення](https://user-images.githubusercontent.com/85941909/206450583-30766ef4-706a-4053-a200-6ecc34075616.png)
![зображення](https://user-images.githubusercontent.com/85941909/206450689-5dec80d5-f199-445b-be21-7dc23db9dca1.png)
![зображення](https://user-images.githubusercontent.com/85941909/206451166-fd35b3a3-1e40-4f3f-95df-00766e3e0cf4.png)


## For Manager
![зображення](https://user-images.githubusercontent.com/85941909/209664685-31811b29-7a92-4d33-87f4-64c77d62b75b.png)
![зображення](https://user-images.githubusercontent.com/85941909/206450867-19d0755a-7cb5-4106-a27e-e19488af3d70.png)
![зображення](https://user-images.githubusercontent.com/85941909/206450922-84e18e2e-8802-434f-8026-f9aafc4ad3b6.png)
![зображення](https://user-images.githubusercontent.com/85941909/206451024-3b4ac1dc-40c6-42bf-879b-5524d6dbe4aa.png)
![зображення](https://user-images.githubusercontent.com/85941909/206451744-7bba8042-ff92-4527-b93b-8a0c5c38d379.png)

## Test Coverage (JACOCO plugin)
![зображення](https://user-images.githubusercontent.com/85941909/209723614-066e70b7-28c1-4ef2-9617-b31634a61f6b.png)


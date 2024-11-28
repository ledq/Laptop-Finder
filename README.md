# Laptop Finder

This repository contains the **Laptop Finder** project, a Java-based application designed to simplify the laptop shopping experience for users. The application provides detailed laptop information, price comparisons, user reviews, and other features to help users make informed purchasing decisions. The back-end is powered by a Microsoft SQL Server database.

## Features

1. **Acquire Laptop Info**: Retrieve detailed information about a laptop, including specifications, price, and sellers.
2. **Leave Comments**: Users can leave comments to discuss the quality and capabilities of laptops.
3. **Leave Ratings**: Users can rate laptops on a scale from 1 to 5 stars, helping others make informed decisions.
4. **Compare Prices**: View multiple sellers for the same laptop and compare prices to find the best deal.
5. **User Logins**: Users can create an account to save wishlists, comments, ratings, and likes on comments.
6. **Create Wishlists**: Users can save laptops to a wishlist for future reference.
7. **Compare Laptop Specs**: Users can compare specifications of different laptops, including RAM, GPU, CPU, screen size, color, and more.
8. **Like Comments**: Users can like comments that they find helpful, making them more visible to others.
![image](https://github.com/user-attachments/assets/8b420517-2b08-40b6-9e7a-b352e2ce59ed)
![image](https://github.com/user-attachments/assets/2dec8cb9-118f-4594-ad38-d88c4ba56cbc)


## How It Works

### Front-End 

The front end is built using Java with a simple graphical user interface (GUI) to provide a seamless experience. Users can input search criteria, press a search button, and receive a list of laptops that match their needs. From there, users can view reviews, leave their own, compare laptops, or add them to their wishlist.

### Back-End 

The back end uses a Microsoft SQL Server database named `LaptopOnly10`. The database contains tables for `Laptop`, `Seller`, `User`, `Wishlist`, `SoldBy`, `Rates`, and `LikesComment`. Stored procedures are utilized to interact with the Java application, ensuring secure and efficient data management.

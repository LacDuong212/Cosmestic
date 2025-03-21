# Cosmetics Store Mobile Application

## Introduction
Welcome to the Cosmetics Store mobile application repository! This Android-based e-commerce platform is designed to provide users with a seamless shopping experience for cosmetic products. Our application features an intuitive user interface, comprehensive product cataloging, and a smooth checkout process.

## Screenshots
Here are some screenshots showcasing the app's interface:

### Intro
<p align="center"> <img src="https://raw.githubusercontent.com/LacDuong212/Cosmestic/refs/heads/master/screenshots/Intro.jpg" width="250" alt="Intro">

### Log In Page
<p align="center"> <img src="https://raw.githubusercontent.com/LacDuong212/Cosmestic/refs/heads/master/screenshots/Log_In.jpg" width="250" alt="Log In">

### Sign Up Page
<p align="center"> <img src="https://raw.githubusercontent.com/LacDuong212/Cosmestic/refs/heads/master/screenshots/Sign_Up.jpg" width="250" alt="Sign Up">

### Home Page
<p align="center"> <img src="https://raw.githubusercontent.com/LacDuong212/Cosmestic/refs/heads/master/screenshots/Home.jpg" width="250" alt="Home">

## Technology Stack
- **Frontend**: Android Native (Java)
- **Backend**: RESTful API using Spring Boot
- **Database**: MySQL
- **Image Loading**: Glide library
- **Networking**: Retrofit for API calls
- **JSON Parsing**: Gson

## Installation and Setup
1. Clone the repository:
   ```
   git clone https://github.com/LacDuong212/Cosmestic.git
   ```
2. Open the project in Android Studio
3. Sync Gradle dependencies
4. Configure the API base URL in `app/src/main/java/com/example/cosmeticapp/service/RetrofitClient.java`
5. Build and run the application on your device or emulator

## API Integration
The app connects to a backend service providing the following endpoints:
- `/api/categories` - Get all product categories
- `/api/products/category/{categoryId}/price-asc` - Get products by category
- `/api/auth` - Authentication endpoints

## Contributing
Contributions are welcome! If you'd like to contribute to this project, please:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Contact
Project Link: [https://github.com/LacDuong212/Cosmestic](https://github.com/LacDuong212/Cosmestic)

---

© 2025 Cosmetics Store Mobile Application

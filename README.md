# UnitConverter

<a href="https://roadmap.sh/projects/unit-converter">Link</a>

---

## **Unit Converter API**

### **Overview**
The Unit Converter API is a Spring Boot-based RESTful application that provides unit conversion functionality for **length**, **weight**, and **temperature** measurements. 

---

### **Features**
- Converts between various units of length (e.g., millimeter to meter).
- Converts between various units of weight (e.g., gram to kilogram).
- Converts between various units of temperature (e.g., Celsius to Fahrenheit).
- Provides a clean and modular structure for future enhancements.

---

### **Technologies Used**
- **Spring Boot:** Backend framework for creating RESTful APIs.
- **Java 17+:** Programming language.
- **Maven:** Dependency and build management.
- **Log4j:** Logging framework (optional in the controller).
- **REST APIs:** Exposes the functionality via HTTP endpoints.

---

### **Endpoints**

#### **Convert Endpoint**
**Path:** `/convert`  
**Method:** `GET`  
**Description:** Converts a value from one unit to another.  

**Query Parameters:**
- `value` (required): The numeric value to convert.
- `prevMeasure` (required): The unit of the input value.
- `finalMeasure` (required): The desired unit to convert to.
- `type` (required): The category of the unit (`length`, `weight`, or `temperature`).

---

### **Setup Instructions**

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-repo-url.git
   cd UnitConverter
   ```

2. **Build the Application:**
   Make sure Maven is installed, then run:
   ```bash
   mvn clean install
   ```

3. **Run the Application:**
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API:**
   By default, the application runs on [http://127.0.0.1:8080](http://127.0.0.1:8080).

---

### **Examples**

#### 1. **Length Conversion**
**Request:**  
```http
GET http://127.0.0.1:8080/convert?value=1000&prevMeasure=millimeter&finalMeasure=meter&type=length
```

**Response:**  
```json
{
    "prevMeasure": "millimeter",
    "currentValue": 1000.0,
    "newValue": 1.0,
    "finalMeasure": "meter"
}
```

---

#### 2. **Weight Conversion**
**Request:**  
```http
GET http://127.0.0.1:8080/convert?value=500&prevMeasure=gram&finalMeasure=kilogram&type=weight
```

**Response:**  
```json
{
    "prevMeasure": "gram",
    "currentValue": 500.0,
    "newValue": 0.5,
    "finalMeasure": "kilogram"
}
```

---

#### 3. **Temperature Conversion**
**Request:**  
```http
GET http://127.0.0.1:8080/convert?value=100&prevMeasure=celsius&finalMeasure=fahrenheit&type=temperature
```

**Response:**  
```json
{
    "prevMeasure": "celsius",
    "currentValue": 100.0,
    "newValue": 212.0,
    "finalMeasure": "fahrenheit"
}
```

---

### **Error Handling**
1. **Missing Parameters:**
   **Request:**  
   ```http
   GET http://127.0.0.1:8080/convert?value=100&prevMeasure=&finalMeasure=meter&type=length
   ```
   **Response:**  
   ```json
   {
       "error": "All parameters (value, prevMeasure, finalMeasure, type) are required."
   }
   ```

2. **Invalid Numeric Value:**
   **Request:**  
   ```http
   GET http://127.0.0.1:8080/convert?value=abc&prevMeasure=gram&finalMeasure=pound&type=weight
   ```
   **Response:**  
   ```json
   {
       "error": "Invalid numeric value."
   }
   ```

3. **Invalid Units:**
   **Request:**  
   ```http
   GET http://127.0.0.1:8080/convert?value=100&prevMeasure=invalidUnit&finalMeasure=meter&type=length
   ```
   **Response:**  
   ```json
   {
       "error": "Invalid units: invalidUnit or meter."
   }
   ```

---

### **Future Enhancements**
- Support for additional measurement types (e.g., volume, speed).
- Extend temperature support for other scales (e.g., Rankine).
- Integration with a front-end interface.
- Add authentication for API access.

---

### **License**
This project is licensed under the [MIT License](LICENSE).

Feel free to contribute and open issues for improvements!

--- 

Let me know if you'd like me to refine or expand any section!

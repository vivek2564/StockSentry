# 📈 StockSentry - Stock Monitoring & Alert Platform

A full-stack stock monitoring platform built using **Spring Boot**, **React**, **MySQL**, and **JWT Authentication**.

---

## 🚀 Features

✅ User Registration & Login  
✅ JWT Authentication & Spring Security  
✅ Personalized Stock Watchlist  
✅ Real-Time Stock Price Tracking (Finnhub API)  
✅ Financial News Integration (NewsAPI)  
✅ Price Alert System  
✅ Email Notifications (SendGrid)  
✅ WhatsApp Notifications (Twilio)  
✅ User Profile Management

---

## 🛠 Tech Stack

### Backend
- Spring Boot
- Spring Security
- JWT
- Spring Scheduler
- JPA/Hibernate
- MySQL

### Frontend
- React
- Axios
- React Router

### APIs
- Finnhub API
- NewsAPI
- Twilio API
- SendGrid API

---

## 📂 Project Structure

```text
StockSentry
│
├── src/                  # Spring Boot Backend
├── frontend/             # React Frontend
├── pom.xml
└── README.md
```

---

## ⚙️ Installation

### Backend

```bash
mvn clean install
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

---

## 🔐 Environment Variables

Create:

```text
application-local.properties
```

Add:

```properties
finnhub.api.key=YOUR_FINNHUB_KEY
newsapi.api.key=YOUR_NEWSAPI_KEY
sendgrid.api.key=YOUR_SENDGRID_KEY
twilio.account.sid=YOUR_TWILIO_SID
twilio.auth.token=YOUR_TWILIO_TOKEN
jwt.secret=YOUR_SECRET
```

---

## 🎯 Future Enhancements

- Portfolio Tracking
- Stock Charts
- Market Analytics
- Advanced Notifications
- Dashboard Improvements

---

## 👨‍💻 Author

**Vivek Kumar**

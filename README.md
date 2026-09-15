✈️ FlightService

FlightService, uçuş operasyonlarının yönetilmesi amacıyla geliştirilmiş Spring Boot tabanlı RESTful Backend API projesidir.

Proje; kullanıcı yönetimi, authentication, yetkilendirme ve uçuş operasyonlarının güvenli ve sürdürülebilir bir mimari üzerinden yönetilmesini hedeflemektedir.

🚀 Özellikler

✈️ Uçuş yönetimi

👤 Kullanıcı yönetimi

🔐 JWT tabanlı authentication

🛡️ Spring Security ile authorization

🗄️ PostgreSQL veritabanı

📦 Spring Data JPA

✉️ E-posta gönderimi

✅ Request validation

📊 Spring Boot Actuator

📈 Prometheus metrics

🧩 Katmanlı mimari

🔄 RESTful API

🛠️ Teknolojiler
Teknoloji	Kullanım
Java 26	Backend geliştirme
Spring Boot	Backend framework
Spring Web	REST API
Spring Data JPA	Veritabanı erişimi
PostgreSQL	Veritabanı
Spring Security	Güvenlik
JWT	Authentication
Spring Validation	Veri doğrulama
Spring Mail	E-posta işlemleri
Lombok	Boilerplate kod azaltma
Spring Actuator	Monitoring
Micrometer	Metrics
Prometheus	Monitoring
Gradle	Build & dependency management
🏗️ Mimari

Proje katmanlı mimari yaklaşımı kullanmaktadır.

                    ┌─────────────────┐
                    │     Client      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Controller    │
                    │    REST API     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │     Service     │
                    │ Business Logic  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Repository    │
                    │   Data Access   │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   PostgreSQL    │
                    └─────────────────┘


Authentication ve authorization işlemleri Spring Security ve JWT kullanılarak gerçekleştirilmektedir.

📂 Proje Yapısı
FlightService/
│
├── gradle/
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │
│   └── test/
│
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md

⚙️ Gereksinimler

Projeyi çalıştırmak için aşağıdaki araçların sisteminizde bulunması gerekir:

Java 26

PostgreSQL

Git

Gradle'ın ayrıca kurulmasına gerek yoktur. Projede bulunan Gradle Wrapper kullanılabilir.

Java sürümünü kontrol etmek için:

java -version

📥 Kurulum

Repository'yi klonlayın:

git clone https://github.com/ilkerCelimli/FlightService.git


Proje klasörüne geçin:

cd FlightService

🗄️ PostgreSQL Ayarları

PostgreSQL üzerinde proje için bir database oluşturun:

CREATE DATABASE flightservice;


Ardından uygulamanın database bağlantı bilgilerini kendi ortamınıza göre yapılandırın.

Örnek:

spring.datasource.url=jdbc:postgresql://localhost:5432/flightservice
spring.datasource.username=postgres
spring.datasource.password=your_password


Not: Gerçek database şifrelerini, JWT secret değerlerini veya diğer hassas bilgileri Git repository'sine göndermeyin.

🔐 Environment Variables

Production ortamında hassas bilgilerin environment variable olarak tutulması önerilir.

Örnek:

DATABASE_USERNAME
DATABASE_PASSWORD
JWT_SECRET
MAIL_USERNAME
MAIL_PASSWORD

▶️ Uygulamayı Çalıştırma
Linux / macOS
./gradlew bootRun

Windows
gradlew.bat bootRun

🔨 Build

Projeyi temizleyip build almak için:

./gradlew clean build


Windows:

gradlew.bat clean build


Build tamamlandıktan sonra JAR dosyası:

build/libs/


klasörü altında oluşturulur.

JAR dosyasını çalıştırmak için:

java -jar build/libs/FlightService-0.0.1-SNAPSHOT.jar

🧪 Test

Unit testleri çalıştırmak için:

./gradlew test


Windows:

gradlew.bat test

🔐 Authentication

FlightService, authentication işlemlerinde Spring Security + JWT yaklaşımını kullanmaktadır.

Genel authentication akışı:

Client
   │
   │ Login
   ▼
Authentication
   │
   ▼
Spring Security
   │
   ▼
JWT Token
   │
   ▼
Protected API


Kullanıcı başarılı bir şekilde giriş yaptıktan sonra elde edilen JWT token, yetkilendirme gerektiren endpoint'lere yapılan isteklerde kullanılmalıdır.

Örnek:

Authorization: Bearer <JWT_TOKEN>

📡 REST API

Uygulama RESTful API yaklaşımını kullanmaktadır.

API endpoint'leri Controller katmanı üzerinden sunulmaktadır.

Genel endpoint yapısı:

/api/...


API endpoint'lerinin detayları proje içerisindeki Controller sınıflarından incelenebilir.

📊 Monitoring

Projede Spring Boot Actuator ve Micrometer Prometheus desteği bulunmaktadır.

Bu yapı sayesinde uygulamanın:

Health durumu

JVM metrikleri

HTTP request metrikleri

Sistem metrikleri

Uygulama performansı

izlenebilir.

Prometheus ile monitoring sistemlerine metrik aktarımı yapılabilir.

🔒 Güvenlik

Projede aşağıdaki güvenlik teknolojileri kullanılmaktadır:

Spring Security

JWT

Password hashing

Authentication

Authorization

Request validation

Production ortamında özellikle aşağıdaki bilgilerin güvenli şekilde yönetilmesi önerilir:

JWT_SECRET
DATABASE_PASSWORD
MAIL_PASSWORD

🧪 Geliştirme

Yeni bir özellik geliştirmek için:

git checkout -b feature/new-feature


Değişikliklerinizi yaptıktan sonra:

git add .
git commit -m "Add new feature"


Branch'i GitHub'a gönderin:

git push origin feature/new-feature


Daha sonra GitHub üzerinden Pull Request oluşturabilirsiniz.

📌 Roadmap

Projeye ilerleyen aşamalarda aşağıdaki özelliklerin eklenmesi planlanabilir:

 Swagger / OpenAPI documentation

 Docker

 Docker Compose

 CI/CD

 Integration tests

 Test coverage

 Redis cache

 Kafka / RabbitMQ

 Centralized logging

 Grafana dashboard

 Daha kapsamlı role-based authorization

🤝 Contributing

Katkıda bulunmak isteyen geliştiriciler repository'yi fork ederek yeni bir branch oluşturabilir ve Pull Request gönderebilir.

Katkılar, hata düzeltmeleri ve yeni özellik önerileri memnuniyetle karşılanır.

📄 License

Bu proje için henüz bir lisans belirtilmemiştir.

👨‍💻 Developer

İlker Celimli

GitHub:

https://github.com/ilkerCelimli

⭐ Eğer proje hoşunuza gittiyse repository'ye star vermeyi unutmayın!

✈️ FlightService

FlightService, havayolu operasyonlarını yönetmek amacıyla geliştirilmiş, Spring Boot tabanlı bir backend uygulamasıdır.

Proje; uçuş, kullanıcı ve havayolu operasyonlarının güvenli bir şekilde yönetilmesini sağlayan RESTful servis mimarisi üzerine kurulmuştur.

🚀 Özellikler

✈️ Uçuş yönetimi

👤 Kullanıcı yönetimi

🔐 JWT tabanlı kimlik doğrulama

🛡️ Spring Security ile yetkilendirme

🗄️ PostgreSQL veritabanı entegrasyonu

📦 Spring Data JPA ile veri erişimi

✉️ E-posta gönderimi

✅ Request validation

📊 Spring Boot Actuator

📈 Prometheus metrikleri

🧩 Katmanlı ve sürdürülebilir backend mimarisi

🛠️ Teknolojiler
Teknoloji	Kullanım Amacı
Java 26	Programlama dili
Spring Boot 4.1.1	Backend framework
Spring Web MVC	REST API geliştirme
Spring Data JPA	ORM ve veri erişimi
PostgreSQL	İlişkisel veritabanı
Spring Security	Authentication & Authorization
JWT	Token tabanlı authentication
Spring Validation	Veri doğrulama
Spring Mail	E-posta işlemleri
Lombok	Boilerplate kodların azaltılması
Actuator	Uygulama monitoring
Micrometer / Prometheus	Metrik toplama
Gradle	Build ve dependency management
🏗️ Mimari

Uygulama, sorumlulukların birbirinden ayrıldığı katmanlı bir mimari yaklaşım kullanacak şekilde tasarlanmıştır.

┌──────────────────────────────┐
│          Client              │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│       REST Controller        │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          Service             │
│     Business Logic Layer     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│        Repository            │
│       Data Access Layer      │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│         PostgreSQL           │
└──────────────────────────────┘


Authentication ve authorization işlemleri Spring Security ve JWT üzerinden gerçekleştirilmektedir.

📁 Proje Yapısı

Projenin temel yapısı Gradle ve Spring Boot üzerine kuruludur:

FlightService/
├── gradle/
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── .gitattributes
├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md

⚙️ Gereksinimler

Projeyi çalıştırmadan önce aşağıdaki araçların sisteminizde bulunması gerekir:

Java 26

PostgreSQL

Git

Gradle'ın ayrıca kurulmasına gerek yoktur. Proje içerisinde bulunan Gradle Wrapper kullanılabilir.

Java sürümünü kontrol etmek için:

java -version

📥 Projeyi Klonlama
git clone https://github.com/ilkerCelimli/FlightService.git
cd FlightService

🗄️ PostgreSQL Yapılandırması

PostgreSQL üzerinde proje için bir veritabanı oluşturun.

Örneğin:

CREATE DATABASE flightservice;


Ardından Spring Boot configuration dosyanızdaki veritabanı bağlantı bilgilerini kendi ortamınıza göre düzenleyin.

Örnek:

spring.datasource.url=jdbc:postgresql://localhost:5432/flightservice
spring.datasource.username=postgres
spring.datasource.password=your_password


Gerçek veritabanı şifrelerini veya JWT secret gibi hassas bilgileri Git repository'sine göndermeyin.

▶️ Uygulamayı Çalıştırma
Linux / macOS
./gradlew bootRun

Windows
gradlew.bat bootRun


Alternatif olarak projeyi build edebilirsiniz:

./gradlew build


Testleri çalıştırmak için:

./gradlew test

🔐 Authentication

Uygulamada authentication mekanizması Spring Security + JWT kullanılarak gerçekleştirilmektedir.

Kullanıcı başarılı bir şekilde authentication işleminden geçtiğinde JWT token oluşturulur ve korumalı endpoint'lere erişim sırasında bu token kullanılır.

Genel authentication akışı:

Client
   │
   │ Login credentials
   ▼
Authentication Endpoint
   │
   ▼
Spring Security
   │
   ▼
JWT Token
   │
   ▼
Protected API

📡 REST API

Uygulamanın REST API endpoint'leri controller katmanı üzerinden sunulmaktadır.

Endpoint'lerin güncel listesi ve request/response modelleri proje içerisindeki controller sınıflarından incelenebilir.

Genel yapı:

/api/...


Endpoint listesi proje geliştikçe bu bölümde detaylandırılabilir.

📊 Monitoring

Uygulamada Spring Boot Actuator ve Micrometer Prometheus desteği bulunmaktadır.

Bu sayede uygulamanın:

Health durumu

Uygulama metrikleri

JVM metrikleri

HTTP request metrikleri

Sistem performans bilgileri

izlenebilir.

Prometheus entegrasyonu sayesinde monitoring sistemlerine metrik aktarımı yapılabilir.

🧪 Test

Testleri çalıştırmak için:

./gradlew test


Windows:

gradlew.bat test


Test sonuçları Gradle'ın oluşturduğu build klasörü içerisinde bulunabilir.

🔨 Build

Production için build almak:

./gradlew clean build


Oluşturulan JAR dosyası:

build/libs/


klasörü altında bulunur.

JAR dosyasını çalıştırmak için:

java -jar build/libs/FlightService-0.0.1-SNAPSHOT.jar

🔒 Güvenlik

Projede güvenlik açısından aşağıdaki teknolojiler kullanılmaktadır:

Spring Security

JWT

Password hashing

Request validation

Authentication / Authorization

Production ortamında aşağıdaki bilgilerin environment variable veya güvenli bir secret management sistemi üzerinden yönetilmesi önerilir:

DATABASE_USERNAME
DATABASE_PASSWORD
JWT_SECRET
MAIL_USERNAME
MAIL_PASSWORD

📈 Gelecek Geliştirmeler

Projeye ilerleyen aşamalarda aşağıdaki özellikler eklenebilir:

 Swagger / OpenAPI dokümantasyonu

 Docker ve Docker Compose desteği

 Daha kapsamlı integration testleri

 CI/CD pipeline

 Redis cache

 Kafka / RabbitMQ entegrasyonu

 Gelişmiş role-based authorization

 Merkezi logging

 Production monitoring dashboard

 Test coverage raporu

🤝 Katkıda Bulunma

Projeye katkıda bulunmak için:

Repository'yi fork edin.

Yeni bir branch oluşturun.

git checkout -b feature/new-feature


Değişikliklerinizi yapın.

Commit oluşturun.

git add .
git commit -m "Add new feature"


Branch'inizi GitHub'a gönderin.

git push origin feature/new-feature


Pull Request oluşturun.

📄 Lisans

Bu repository için henüz belirlenmiş bir lisans bilgisi bulunmamaktadır.

👨‍💻 Geliştirici

İlker Celimli

GitHub: @ilkerCelimli

⭐ Projeyi faydalı bulduysanız repository'ye star bırakabilirsiniz.

FROM maven:3.8.4-openjdk-17

WORKDIR /app

# คัดลอก pom.xml ก่อน
COPY pom.xml ./

# ดาวน์โหลด dependencies
RUN mvn dependency:go-offline

# คัดลอกซอร์สโค้ดทั้งหมด
COPY src ./src

# Compile the application
RUN mvn compile

# Build และรัน application
CMD ["mvn", "spring-boot:run"]
FROM openjdk:11-jre-slim

# ติดตั้ง wget, tar, procps, และ nano
RUN apt-get update \
    && apt-get install -y wget tar procps nano \
    && rm -rf /var/lib/apt/lists/*

# กำหนดค่าตัวแปรสำหรับเวอร์ชันของ ActiveMQ และ Hawtio
ENV ACTIVEMQ_VERSION 5.17.1
ENV HAWTIO_VERSION 2.14.0

# ดาวน์โหลดและติดตั้ง ActiveMQ
RUN wget https://archive.apache.org/dist/activemq/$ACTIVEMQ_VERSION/apache-activemq-$ACTIVEMQ_VERSION-bin.tar.gz -O /tmp/activemq.tar.gz \
    && tar xzf /tmp/activemq.tar.gz -C /opt \
    && mv /opt/apache-activemq-$ACTIVEMQ_VERSION /opt/activemq \
    && rm /tmp/activemq.tar.gz

# ดาวน์โหลดและติดตั้ง Hawtio
RUN wget https://repo1.maven.org/maven2/io/hawt/hawtio-default/$HAWTIO_VERSION/hawtio-default-$HAWTIO_VERSION.war -O /opt/activemq/webapps/hawtio.war

# กำหนดค่า ActiveMQ และโฟลเดอร์สำหรับข้อมูล
ENV ACTIVEMQ_HOME /opt/activemq
ENV ACTIVEMQ_BASE $ACTIVEMQ_HOME
ENV ACTIVEMQ_CONF $ACTIVEMQ_HOME/conf
ENV ACTIVEMQ_DATA $ACTIVEMQ_HOME/data

COPY custom-env.sh /opt/activemq/bin/
COPY jetty-realm.properties /opt/activemq/conf/
COPY login.config /opt/activemq/conf/
COPY users.properties /opt/activemq/conf/
COPY groups.properties /opt/activemq/conf/

RUN ls -l /opt/activemq/conf/

EXPOSE 61616 8161

CMD ["/bin/bash", "-c", "source /opt/activemq/bin/custom-env.sh && /opt/activemq/bin/activemq console"]
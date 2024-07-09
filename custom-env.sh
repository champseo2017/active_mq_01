#!/bin/bash

# เพิ่ม Hawtio เข้าไปใน classpath
CLASSPATH="$CLASSPATH:$ACTIVEMQ_HOME/webapps/hawtio.war"
export CLASSPATH

# กำหนดค่า Hawtio
export HAWTIO_ROLE=admins
export HAWTIO_REALM=ActiveMQRealm

# กำหนดค่า activemq.conf และ activemq.home
export ACTIVEMQ_CONF=$ACTIVEMQ_HOME/conf
export ACTIVEMQ_HOME=/opt/activemq

# เพิ่มการกำหนดค่า JAAS และการดีบัก
export JAVA_OPTS="$JAVA_OPTS -Djavax.net.debug=ssl,handshake -Djava.security.debug=all"

export JAVA_OPTS="$JAVA_OPTS -Djava.security.auth.login.config=$ACTIVEMQ_HOME/conf/login.config"

# เพิ่มการกำหนดค่า Jetty สำหรับ Hawtio
export ACTIVEMQ_OPTS="$ACTIVEMQ_OPTS -Dhawtio.realm=ActiveMQRealm -Dhawtio.role=admins -Dhawtio.authenticationEnabled=true"

echo "JAVA_SECURITY_AUTH_LOGIN_CONFIG=$JAVA_SECURITY_AUTH_LOGIN_CONFIG"
echo "Contents of login.config:"
cat $ACTIVEMQ_HOME/conf/login.config

echo "ACTIVEMQ_HOME=$ACTIVEMQ_HOME"
echo "ACTIVEMQ_CONF=$ACTIVEMQ_CONF"
echo "JAVA_OPTS=$JAVA_OPTS"
echo "ACTIVEMQ_OPTS=$ACTIVEMQ_OPTS"
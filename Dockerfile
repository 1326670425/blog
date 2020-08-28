FROM adoptopenjdk/openjdk8-openj9
VOLUME /tmp

ARG TIME_ZONE=Asia/Shanghai
ARG PROJECT=blog

WORKDIR /root/${PROJECT}

ENV TZ=${TIME_ZONE}
ENV JAVA_OPTS="-Xms256m -Xmx256m"

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -server -jar blog.jar

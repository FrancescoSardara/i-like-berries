FROM azul/zulu-openjdk-alpine:13-jre

LABEL maintainer=fr.sardara@gmail.com

WORKDIR /app

COPY libs libs/

COPY resources resources/

COPY classes classes/

COPY entrypoint.sh entrypoint.sh

RUN apk update \
    && apk add jq \
    && chmod u+x entrypoint.sh 

ENTRYPOINT ["./entrypoint.sh"]
 
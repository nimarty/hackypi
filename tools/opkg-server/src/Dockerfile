FROM nginx:alpine as base

LABEL org.opencontainers.image.source=https://github.com/nimarty/hackypi
LABEL org.opencontainers.image.description="OPKG Server"
LABEL org.opencontainers.image.licenses=MIT

ARG PACKAGE_DIR
ARG OPKG_VERSION=0.5.0

RUN apk update &&  \
    apk add git python3 && \
    rm /etc/nginx/conf.d/default.conf

COPY nginx/. /etc/nginx/conf.d/
COPY bin/ /usr/bin/.
COPY ${PACKAGE_DIR} /packages/

RUN git clone -b ${OPKG_VERSION} \
    https://git.yoctoproject.org/git/opkg-utils \
    -c advice.detachedHead=false && \
    rm -rf opkg-utils/tests && \
    cp /opkg-utils/* /usr/bin/ && \
    chmod -R 755 /usr/bin

WORKDIR /opkg
EXPOSE 80
ENTRYPOINT [ "/usr/bin/start-opkg-server.sh" ]

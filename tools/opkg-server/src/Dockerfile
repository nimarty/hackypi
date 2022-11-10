FROM nginx:1.23.2-alpine

LABEL org.opencontainers.image.source=https://github.com/nimarty/hackypi
LABEL org.opencontainers.image.description="OPKG Server"
LABEL org.opencontainers.image.licenses=MIT

ARG PACKAGE_DIR
ARG OPKG_VERSION=0.5.0

RUN apk update &&  \
    apk add git python3

RUN  rm /etc/nginx/conf.d/default.conf
COPY nginx/. /etc/nginx/conf.d/

COPY bin/ /usr/bin/.
COPY ${PACKAGE_DIR} /packages/

RUN git clone --depth 1 -b ${OPKG_VERSION} --single-branch \
    -c advice.detachedHead=false \
    https://git.yoctoproject.org/git/opkg-utils /usr/bin/opkg-utils && \
    rm -rf /usr/bin/opkg-utils/tests

RUN /usr/bin/update_package_list.sh
RUN (crontab -l ; echo "* * * * * update_package_list.sh") | sort | uniq | crontab -

ENTRYPOINT [ "entrypoint.sh" ]


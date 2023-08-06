SUMMARY = "Tearful Tanja challenge"
DESCRIPTION = "Package installing and setup the Bluetooth and RFCOMM service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit pkgconfig systemd

SRC_URI = " \
    file://bluetooth.service \
    file://rfcomm.service \
    file://accept_bt_pin_requests.exp \
    file://accept_bt_service_requests.exp \
    "

RDEPENDS:${PN} = " \
    expect \
    cronie \
    "

pkg_preinst:${PN}() {
  echo "[x] Remove existing services"
  systemctl stop bluetooth
  rm -f /lib/systemd/system/bluetooth.service
  hciconfig hci0 up
}

do_install () {
  # install systemd services
  echo "[x] Install bluetooth and rfcomm services"
  install -d ${D}${systemd_unitdir}/system/
  install -m 0644 ${WORKDIR}/bluetooth.service ${D}/${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/rfcomm.service ${D}/${systemd_unitdir}/system

  # install expect scripts
  echo "[x] Install bluetooth connection handler scripts"
  install -d ${D}/home/root/
  install -m 0744 ${WORKDIR}/accept_bt_pin_requests.exp ${D}/home/root
  install -m 0744 ${WORKDIR}/accept_bt_service_requests.exp ${D}/home/root
}

pkg_postinst:${PN} () {
  # reload services
  echo "[x] Reload services"
  systemctl daemon-reload
  systemctl start bluetooth
  systemctl start rfcomm
  systemctl enable rfcomm

  # password is "raspberry", created with command mkpasswd
  echo "[x] Setup target account"
  useradd -p '$6$raspberry$pAncZXWz4UlemxLv4Xh4FPSjcyxNqYA9bNUcJWTGZFhmq8pj86A.zwgAWpwe8vJy3uc2/aoH67hgXn.Ng3o4i1' pi
  echo 'There was a time before the internet, can you believe it?' > /home/pi/treasure
  chown pi /home/pi/treasure
  chmod 600 /home/pi/treasure

  # add cron jobs to accept incoming bluetooth connections
  echo "[x] Setup cronjobs"
  echo "* * * * * /home/root/accept_bt_pin_requests.exp" >> tmp
  echo "* * * * * /home/root/accept_bt_service_requests.exp" >> tmp
  crontab tmp && rm tmp
  chmod +x /home/root/accept_bt_pin_requests.exp
  chmod +x /home/root/accept_bt_service_requests.exp
}

pkg_postrm:${PN}() {
    # remove user and its files
    echo "[x] Delete user files"
    userdel -fr pi
    systemctl daemon-reload
    systemctl stop rfcomm
    systemctl stop bluetooth
    systemctl disable rfcomm
    rm -f /lib/systemd/system/rfcomm.service
    systemctl daemon-reload
    hciconfig hci0 up

    # remove cron jobs
    echo "[x] Delete cronjobs"
    crontab -r
    rm -f /home/root/accept_bt_pin_requests.exp
    rm -f /home/root/accept_bt_service_requests.exp
}

FILES:${PN} = " \
    ${systemd_unitdir}/system/* \
    /home/root/* \
    "
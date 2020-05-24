FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://cdc-ethernet.cfg"
KERNEL_MODULE_AUTOLOAD += "g_ether"

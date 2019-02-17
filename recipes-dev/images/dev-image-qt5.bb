
#from sources/meta-variscite-fslc/recipes-fsl/images/fsl-image-qt5.bb

DESCRIPTION = "Freescale Image - Adds Qt5"
LICENSE = "MIT"

require dev-image-gui.bb

inherit distro_features_check populate_sdk_qt5

CONFLICT_DISTRO_FEATURES = "directfb"

# Install fonts
QT5_FONTS = " \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-sans \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-sans-mono \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
"

# Install QT5 demo applications
QT5_IMAGE_INSTALL = " \
    packagegroup-qt5-demos \
    ${QT5_FONTS} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxkbcommon', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland qtwayland-plugins', '', d)} \
"

QT5_IMAGE_INSTALL_append_imxgpu3d = " \
    packagegroup-qt5-3d \
    packagegroup-qt5-webkit \
"

# Most of QtWebEngine demo are currently broken.
# If you want to test them uncomment the following line
# QT5_IMAGE_INSTALL_append_imxgpu3d = " packagegroup-qt5-webengine"

IMAGE_INSTALL += " \
    ${QT5_IMAGE_INSTALL} \
"

IMAGE_INSTALL_append = " \
    qtmultimedia \
    qtmultimedia-plugins \
"
# Due to the Qt samples the resulting image will not fit the default NAND size.
# Removing default ubi creation for this image
IMAGE_FSTYPES_remove = "multiubi"

add_my_files() {
	cp -L ${THISDIR}/files/root_home/.profile  ${IMAGE_ROOTFS}/home/root/
}

ROOTFS_POSTPROCESS_COMMAND += "add_my_files;"

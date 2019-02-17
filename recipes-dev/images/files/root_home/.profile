#!/bin/sh

export DISPLAY=:0

xrandr --newmode "800x480_nh" 32 800 844 892 932 480 493 496 525 -hsync -vsync
xrandr --newmode "800x480_mo" 33 800 840 888 928 480 493 496 525 -hsync -vsync
xrandr --addmode "DISP3 BG" "800x480_nh"
xrandr --output "DISP3 BG" --mode "800x480_nh"
xrandr --addmode "DISP3 BG" "800x480_mo"
xrandr --output "DISP3 BG" --mode "800x480_mo"

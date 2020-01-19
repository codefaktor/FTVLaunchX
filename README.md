FTVLaunchX — README
===================

<p align="center">
    <img src="./app/src/main/res/mipmap-xxhdpi/ic_launcher.png" />
</p>

About
-----

FTVLaunchX is a simple helper application to allow custom launchers on
Amazon Fire TV devices.

It is inspired by the famous
[LauncherHijack](https://github.com/BaronKiko/LauncherHijack) of @BaronKiko
and tries to overcome several issues relating to it.

So far FTVLaunchX has been tested on:

-   Fire TV Stick 2nd Generation (Fire OS 5.2.7.2)
-   Fire TV Stick 4K (Fire OS 6.2.6.8)

Please let me know if you are using it successfully on any other devices.

### Features

-   Reliably intercepts any & all presses of the home button.

-   Starts the selected launcher on boot (note that this can take
    up to a minute on older devices).

-   Starts the selected launcher when waking up from sleep.

-   Allows use of the original long-press-on-home functionality
    by holding down the menu key in parallel.

-   Works with any launcher or other app like e.g.
    -   [Android TV Launcher](https://leanback-launcher.en.aptoide.com/)
    -   [Leanback Launcher](https://github.com/tsynik/LeanbackLauncher)
    -   [TV Launcher 3](https://tvlauncher.en.aptoide.com/)
    -   [Kodi](https://kodi.tv/)

#### Known Limitations

-   When a screensaver is running then the home button does not cause
    the screensaver to exit, all other buttons do work though.

-   Long-pressing the home button had to be replaced by a custom key
    combination (holding down the menu key in parallel).

Please let me know if you find any other issues by opening a bug report at
the [issue tracker][GITHUB_ISSUES] here at GitHub – you are also very welcome
to send in any feature requests.

### Donations

FTVLaunchX is and will always be totally __free__ and __without ads__.

But if you want to say _Thank You_ and would like to support any further
development then feel free to send me a buck via PayPal:

[FTVLaunchX MoneyPool at PayPal](https://paypal.me/pools/c/8lNMBi7om0)

You can also help me out by downloading & using the Brave browser or,
when you are already using Brave, by tipping me some BAT directly at my
[personal GitHub profile](https://github.com/ErikAbele).

<p align="center">
    <a href="https://brave.com/eri226" title="Download Brave">
        <img src="https://brave.com/wp-content/uploads/2018/02/switch_banner_2@2x.png" />
    </a>
</p>

Note: you will have to use Brave minimally for at least one month so that
I get credited the referral bonus.


Documentation
-------------

### Setup

#### Quick Installation Overview

FTVLaunchX needs the `WRITE_SECURE_SETTINGS` permission to enable an
accessibility service on your Fire TV device.

If you know what you are doing then simply grant this permission by executing
the following command in an `ADB` shell on your device:
```
$ pm grant de.codefaktor.ftvlaunchx android.permission.WRITE_SECURE_SETTINGS
```

For a step-by-step guide and more information on how to connect to your
Fire TV device via `ADB`, follow the detailed installation instructions below.

#### Detailed Installation Instructions

There are three ways to install FTVLaunchX: either directly on your Fire TV
device via e.g. the Downloader app or, alternatively with the help of `ADB`
from a mobile phone running Android or a desktop PC.

##### Pre-Requisites

1.  From the main screen of your Fire TV select _Settings_
2.  Select _My Fire TV_ > _Developer Options_
3.  Turn on _ADB Debugging_
4.  Turn on _Apps from Unknown Sources_
5.  Go back to _My Fire TV_ > _About_ > _Network_
6.  Note the IP address of your device; you will need this later on

##### Method 1: directly on Fire TV

1.  Install the Downloader app from the Amazon App Store on your
    Fire TV device
2.  Open Downloader and navigate to
    `github.com/codefaktor/FTVLaunchX/releases`
3.  Find, download and install the latest release of FTVLaunchX
4.  Open Downloader and navigate to
    `troypoint.com/radb`
5.  The download should start automatically; click `Install`, then `Open`
6.  Follow the on-screen instructions and enter the IP address of your
    device when asked (see 6. in Pre-Requisites above)
7.  When presented with a command-line within the `Remote ADB Shell`
    application, enter and run the following command:
```
pm grant de.codefaktor.ftvlaunchx android.permission.WRITE_SECURE_SETTINGS
```
8.  Open FTVLaunchX, select a launcher and press the home button – enjoy!

##### Method 2: via ADB from an Android phone

1.  Use a browser to download the latest release of FTVLaunchX from the
    [release page][GITHUB_ISSUES] to your phone
2.  Install the [Easy Fire Tools](https://play.google.com/store/apps/details?id=de.agondev.easyfiretools)
    application from Google Play on your phone
3.  Install the [Remote ADB Shell](https://play.google.com/store/apps/details?id=com.cgutman.androidremotedebugger)
    application from Google Play on your phone
4.  Open `Easy Fire Tools`, discover & connect to your Fire TV and install
    the downloaded release of FTVLaunchX
5.  Open `Remote ADB Shell`, connect to your Fire TV by using the IP address
    of your device (see 6. in Pre-Requisites above) and run the following
    command:
```
pm grant de.codefaktor.ftvlaunchx android.permission.WRITE_SECURE_SETTINGS
```
6.  Open FTVLaunchX on your Fire TV device, select a launcher and press the
    home button – enjoy!

##### Method 3: via ADB from a desktop PC or laptop

1.  Use a browser to download the latest release of FTVLaunchX from the
    [release page][GITHUB_ISSUES] to your computer
2.  Install `ADB` and connect to your Fire TV by using the IP address
    of your device (see 6. in Pre-Requisites above), see
    [here](https://developer.amazon.com/docs/fire-tv/connecting-adb-to-device.html)
    for detailed instructions on how to do this
3.  Install the downloaded release of FTVLaunchX by executing one of the
    following commands (make sure to use the actual name of the downloaded
    file):
    -   For devices running Fire OS 5 (Fire TV Stick 2nd Generation)
```
adb install FTVLaunchX-x.y.z.apk
```
    -   For devices running Fire OS 6 or 7 (Fire TV Stick 4K & Fire TV Cube)
```
adb install -g FTVLaunchX-x.y.z.apk
```
4.   If your device is not running Fire OS 6 or 7 then grant the required
     permission by running, otherwise proceed to step 5:
```
adb pm grant de.codefaktor.ftvlaunchx android.permission.WRITE_SECURE_SETTINGS
```
5.  Open FTVLaunchX on your Fire TV device, select a launcher and press the
    home button – enjoy!

Note: if you are updating FTVLaunchX with this method then you will need to
add the `-r` flag to the `adb install` commands in step 3.

### Usage

Just start FTVLaunchX and select the application to use as your launcher.

From now on, every time you boot your device, wake it up from sleep or press
the home button, you will be taken to this application.

As long as no launcher has been selected, FTVLaunchX will open itself.

#### The long-press-on-home menu

To access the original long-press-on-home menu or to open the settings menu
of your Fire TV device, you will have to press and hold the menu and home
buttons at the same time.

You can also get to the default Amazon home screen by using this method.

Lean back & enjoy!


Copyright
---------

Copyright © 2020 [Erik Abele](http://www.codefaktor.de/).

All rights not explicitly granted by the [LICENSE][] are reserved.


License
-------

Licensed under the Apache License, Version 2.0 (the "License").

A copy of the License is included in the "[LICENSE][]" text file;
you may also obtain a copy at

  > <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and
limitations under the License.

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

[LICENSE]: ./LICENSE

[GITHUB_ISSUES]: https://github.com/codefaktor/FTVLaunchX/issues
[GITHUB_RELEASES]: https://github.com/codefaktor/FTVLaunchX/releases

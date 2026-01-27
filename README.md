# TechBreakPlugin

- /tb on | off  - for enable\disabel
- /tb add <player>  - for add player to TB whitelist
- /tb reload | rl  - for reload config.yml
- /tb remove <player>  - remove from TB whitelist


## your config.yml file should look like this (comments not important)
```
# tb is tech break if you don't know

save-tb-state: true
# save the tb state to 'isTB' boolean
# if false tb state always sets false after server restart

isTB: false
# that is tb state
# if true tech break is enabled and if false it disabled

tb-message: "&aTech break is enabled!"
# you can use & or § for formatting just how you want
# for § just press hold and press 21(on numpad) then release alt

passed-uuids: []
# using command: /tb add <PlayerName>, you can add uuid
# or using /tb remove <PlayerName>, you can remove uuid

# #NoobGuideComments!
```

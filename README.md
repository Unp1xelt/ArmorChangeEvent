# ArmorChangeEvents

Sadly Spigot does not have an event for checking when a Player is changing armor.
<a href="https://github.com/Arnuh">Arnuh</a> put a good base with his <a href="https://github. com/Arnuh/ArmorEquipEvent/">project</a>, but there where still a lot of bugs. <br>
This plugin should be bug free and working properly, but if you still find some 
please report them. Thank you! <br>
<br>
The events handle when a player
<ul>
    <li>Equip or unequip item in opened inventory through</li>
    <ul>
        <li>Click</li>
        <li>Double click (collect to cursor)</li>
        <li>Shift-click</li>
        <li>Drop item (Q)</li>
        <li>Hotswap with hotbar(1-9) or with second hand(F)</li>
        <li>Dragged item</li>
    </ul>
    <li>Equip item through hotbar right-click</li>
    <li>Equip item by Dispenser</li>
    <li>Unequip when Player dies</li>
    <li>Unequip when armor item breaks</li>
</ul>

## Caution

I've tested this code only on the 1.16.5 version. It could be that some things 
changed in newer versions. <br>
As said before if you find bugs please report them. Thank you!


## Events

All armor events of this plugin are ``Cancellable``. The ``ArmorAction`` gives you info
how the armor was equipped or unequipped. When checking in which slot the item 
was equipped or unequipped use the ``getArmorSlot()`` method.

### ArmorEquipEvent

This event is called when a Player is equipping armor.<br>
<br>
In this example we give the Player the jump boost effect when he equips iron boots.
```java
@EventHandler
private void onArmorEquipEvent(ArmorEquipEvent e) {
    if (e.getItem().getType() == Material.IRON_BOOTS) {
        PotionEffect effect = new PotionEffect(PotionEffectType.JUMP, 1000000, 2);
        e.getPlayer().addPotionEffect(effect);
    }
    ...
}
```

### ArmorUnequipEvent

This event is called when a Player is fully unequipping an item out of the armor 
slot. This means it will only be called when the armor slot is empty. In example,
you custom equipped a stack of dirt on the boots slot and take half of it, it 
will not count as an unequip event. <br>
<br>
And here we remove the jump boost effect when the Player unequips iron boots.
```java
@EventHandler
private void onArmorUnequipEvent(ArmorUnequipEvent e) {
    if (e.getAction() == ArmorAction.DEATH) return;
    if (e.getItem().getType() == Material.IRON_BOOTS) {
        e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
    }
    ...
}
```

<br>**Have fun coding** :heart::fox_face:
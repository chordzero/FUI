package kr.kro.chordzero.fui.controllers

import kr.kro.chordzero.fui.ui.InventoryUI
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

object InventoryController {
    var uiInventories = arrayListOf<InventoryUI>()

    fun register(inventoryUI: InventoryUI) {
        uiInventories.add(inventoryUI)
    }

    fun unregister(inventoryUI: InventoryUI) {
        uiInventories.remove(inventoryUI)
    }

    object EventHandler : Listener {
        @org.bukkit.event.EventHandler
        fun onInventoryClick(event: InventoryClickEvent) {
            uiInventories.forEach { ui ->
                if (ui.inventory != event.clickedInventory) return@forEach
                ui.callback?.invoke(event)
                if (ui.isSlotLocked(event.slot)) {
                    event.isCancelled = true
                    return
                }
            }
        }

        @org.bukkit.event.EventHandler
        fun onInventoryDrag(event: InventoryDragEvent) {
            uiInventories.forEach { ui ->
                if (ui.inventory != event.inventorySlots) return@forEach
                // 인벤토리에서 드래그 한 범위 중 잠겨있는 슬롯이 있는 경우
                if (ui.getLockedSlots().intersect(event.inventorySlots).isNotEmpty()) {
                    event.isCancelled = true
                    return
                }
            }
        }

        @org.bukkit.event.EventHandler
        fun onInventoryClose(event: InventoryCloseEvent) {
            uiInventories.forEach { ui ->
                if (event.inventory != ui.inventory) return@forEach
                if (ui.autoDestroy) {
                    ui.destroy()
                    return
                }
            }
        }
    }
}
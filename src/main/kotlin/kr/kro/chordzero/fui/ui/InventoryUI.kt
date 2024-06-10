package kr.kro.chordzero.fui.ui

import kr.kro.chordzero.fui.controllers.InventoryController
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryClickEvent

open class InventoryUI(val line: Int, val name: Component, var autoDestroy: Boolean = true) {
    init {
        if (line > 6)
            throw Exception("Rows cannot be greater than 6")

        InventoryController.register(this)
    }

    protected var locks = mutableSetOf<Int>()
    val inventory = Bukkit.createInventory(null, 9 * line, name)
    var callback: ((InventoryClickEvent) -> Unit)? = null

    fun isSlotLocked(slot: Int): Boolean {
        return slot in this.locks
    }

    fun getLockedSlots(): Set<Int> {
        return this.locks.toSet()
    }

    fun lock(vararg slots: Int) {
        this.locks.addAll(slots.toSet())
    }

    fun unlock(vararg slots: Int) {
        this.locks.removeAll(slots.toSet())
    }

    fun destroy() {
        InventoryController.unregister(this)
    }
}

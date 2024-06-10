package kr.kro.chordzero.fui

import kr.kro.chordzero.fui.controllers.InventoryController
import org.bukkit.plugin.java.JavaPlugin

class FUI {
    companion object {
        fun setup(plugin: JavaPlugin) {
            plugin.let {
                it.server.pluginManager.registerEvents(InventoryController.EventHandler, it)
            }
        }
    }
}
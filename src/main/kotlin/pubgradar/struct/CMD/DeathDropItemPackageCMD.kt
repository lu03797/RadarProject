package wumo.pubg.struct.CMD

import pubg.radar.debugln
import pubgradar.deserializer.channel.ActorChannel.Companion.airDropLocation
import pubgradar.struct.*
import pubgradar.struct.CMD.ActorCMD
import pubgradar.struct.CMD.updateItemBag
import wumo.pubg.struct.*

object DeathDropItemPackageCMD {
  fun process(actor:Actor,bunch:Bunch,repObj:NetGuidCacheObject?,waitingHandle:Int,data:HashMap<String,Any?>):Boolean {
   try{ with(bunch) {
      when (waitingHandle) {
        6 -> {
          repMovement(actor)
          airDropLocation[actor.netGUID]=actor.location
        }
        16 -> updateItemBag(actor)
        else -> return ActorCMD.process(actor,bunch,repObj,waitingHandle,data)
      }
      return true
    }
   }catch (e: Exception){ debugln{("DeathDropItemPackageCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}")}
   }
 return false
  }
}
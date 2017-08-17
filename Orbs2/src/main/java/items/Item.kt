package items;

/**
* A basic outline for an item in the game.
*/
abstract class Item(var name: String, var identifier: String) {

    /********************
	*					*
	*	  VARIABLES		*
	*					*
	*********************/
    
    var quantity: Int = 1
    
    var acquiredMessage: String = ""



	init {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}
    
    
    /********************
	*					*
	*	   GETTERS		*
	*					*
	*********************/
    
    override fun toString(): String {
        return "[ITEM: $name, QUANTITY: $quantity, IDENTIFIER: $identifier]"
    }
    

	/** Returns a clone of this item. */
	abstract fun clone(): Item

    
    
    /********************
	*					*
	*	   SETTERS		*
	*					*
	*********************/

}



/************************
*						*
*		KEY ITEMS		*
*						*
*************************/

// Orb
class Orb(): Item("Orb", "ORB") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Orb {
		val itm = Orb()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Bucket of Water
class BucketOfWater(): Item("Bucket of Water", "BUCKET_OF_WATER") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): BucketOfWater {
		val itm = BucketOfWater()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Rope
class Rope(): Item("Rope", "ROPE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Rope {
		val itm = Rope()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Coin
class Coin(): Item("Coin", "COIN") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Coin {
		val itm = Coin()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Batch of Cookies
class BatchOfCookies(): Item("Batch of Cookies","BATCH_OF_COOKIES") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): BatchOfCookies {
		val itm = BatchOfCookies()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Dog (Task)
class Dog(): Item("Dog", "DOG") {
	fun setAcquiredMessage() {
		acquiredMessage = "You take the $name along with you!"
	}

	override fun clone(): Dog {
		val itm = Dog()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Pool Membership Card
class PoolMembershipCard(): Item("Pool Membership Card", "POOL_MEMBERSHIP_CARD") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): PoolMembershipCard {
		val itm = PoolMembershipCard()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Lab Key 1
class LabKey1(): Item("Key 1", "KEY_1") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): LabKey1 {
		val itm = LabKey1()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Museum Card
class MuseumCard(): Item("Museum Card", "MUSEUM_CARD") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): MuseumCard {
		val itm = MuseumCard()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Snacks
class Snacks(): Item("Party Snacks", "PARTY_SNACKS") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Snacks {
		val itm = Snacks()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Pickaxe
class Pickaxe(): Item("Pickaxe", "PICKAXE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Pickaxe {
		val itm = Pickaxe()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Hatchet
class Hatchet(): Item("Hatchet", "HATCHET") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Hatchet {
		val itm = Hatchet()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Car Parts
class CarParts(): Item("Car Parts", "CAR_PARTS") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): CarParts {
		val itm = CarParts()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Space Suit
class SpaceSuit(): Item("Space Suit", "SPACE_SUIT") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): SpaceSuit {
		val itm = SpaceSuit()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Dinner Coupon
class DinnerCoupon(): Item("Dinner Coupon", "DINNER_COUPON") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): DinnerCoupon {
		val itm = DinnerCoupon()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Bales of Hay
class BalesOfHay(): Item("Bales of Hay", "BALES_OF_HAY") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): BalesOfHay {
		val itm = BalesOfHay()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// House Key
class HouseKey(): Item("House Key", "HOUSE_KEY") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): HouseKey {
		val itm = HouseKey()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Secret Code
class SecretCode(): Item("Secret Code", "SECRET_CODE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): SecretCode {
		val itm = SecretCode()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Birthday Gift
class BirthdayGift(): Item("Birthday Gift", "BIRTHDAY_GIFT") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): BirthdayGift {
		val itm = BirthdayGift()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Rubber Ball
class RubberBall(): Item("Rubber Ball", "RUBBER_BALL") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): RubberBall {
		val itm = RubberBall()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Room Key
class RoomKey(): Item("Room Key", "ROOM_KEY") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): RoomKey {
		val itm = RoomKey()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Basket
class Basket(): Item("Basket", "BASKET") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Basket {
		val itm = Basket()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Apple
class Apple(): Item("Apple", "APPLE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Apple {
		val itm = Apple()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Orange
class Orange(): Item("Orange", "ORANGE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Orange {
		val itm = Orange()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Corn
class Corn(): Item("Corn", "CORN") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Corn {
		val itm = Corn()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Lettuce
class Lettuce(): Item("Lettuce", "LETTUCE") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Lettuce {
		val itm = Lettuce()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Turkey
class Turkey(): Item("Turkey", "TURKEY") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Turkey {
		val itm = Turkey()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Bread
class Bread(): Item("Bread", "BREAD") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Bread {
		val itm = Bread()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Yeast
class Yeast(): Item("Yeast", "YEAST") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): Yeast {
		val itm = Yeast()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}


// Beach House Key
class BeachHouseKey(): Item("Beach House Key", "BEACH_HOUSE_KEY") {
	fun setAcquiredMessage() {
		if(quantity > 1)
			acquiredMessage = "You received $quantity ${name}s!"
		else
			acquiredMessage = "You received $quantity ${name}!"
	}

	override fun clone(): BeachHouseKey {
		val itm = BeachHouseKey()
		itm.quantity = this.quantity
		itm.acquiredMessage = this.acquiredMessage
		return itm
	}
}
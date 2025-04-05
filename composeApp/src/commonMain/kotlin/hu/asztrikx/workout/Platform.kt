package hu.asztrikx.workout

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
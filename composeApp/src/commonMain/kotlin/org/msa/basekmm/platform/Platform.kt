package org.msa.basekmm.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
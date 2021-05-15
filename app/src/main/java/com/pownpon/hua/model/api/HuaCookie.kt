package com.pownpon.hua.model.api

import okhttp3.Cookie

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: HuaCookie
 * Author: HUA
 * Date: 2021/5/15 16:57
 * Description:
 * History:
 */
data class HuaCookie(
    val domain: String,
    val expiresAt: Long,
    val hostOnly: Boolean,
    val httpOnly: Boolean,
    val name: String,
    val path: String,
    val persistent: Boolean,
    val secure: Boolean,
    val value: String
) {
    constructor(cookie: Cookie) : this(
        cookie.domain(),
        cookie.expiresAt(),
        cookie.hostOnly(),
        cookie.httpOnly(),
        cookie.name(),
        cookie.path(),
        cookie.persistent(),
        cookie.secure(),
        cookie.value()
    )

    fun toCookie(): Cookie {
        return Cookie.Builder()
            .domain(domain)
            .expiresAt(expiresAt)
            .hostOnlyDomain(domain)
            .httpOnly()
            .name(name)
            .path(path)
            .secure()
            .value(value)
            .build()
    }
}

package com.aldyaz.bythenvideo.datasource.upload.model

import com.google.gson.annotations.SerializedName

data class UploadVideoDto(
    @SerializedName("public_id")
    var publicId: String? = null,
    @SerializedName("asset_id")
    var assetId: String? = null,
    @SerializedName("version")
    var version: Int? = null,
    @SerializedName("signature")
    var signature: String? = null,
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("resource_type")
    var resourceType: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("bytes")
    var bytes: Int? = null,
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("access_mode")
    var accessMode: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("secure_url")
    var secureUrl: String? = null,
    @SerializedName("etag")
    var etag: String? = null
)
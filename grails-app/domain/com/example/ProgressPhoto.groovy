package com.example

class ProgressPhoto {

    User user
    String originalFilename
    String storedFilename
    String contentType
    Long fileSize
    String caption

    Date dateCreated
    Date lastUpdated

    static belongsTo = [user: User]

    static constraints = {
        user nullable: false
        originalFilename blank: false, maxSize: 255
        storedFilename blank: false, unique: true, maxSize: 255
        contentType blank: false, inList: ['image/png', 'image/jpeg', 'image/jpg', 'image/gif', 'image/webp']
        fileSize nullable: false, min: 1L
        caption nullable: true, maxSize: 200
    }

    static mapping = {
        caption type: 'text'
        sort dateCreated: 'desc'
    }

    String toString() {
        caption ?: originalFilename
    }
}

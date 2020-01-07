# book-service
Get book details from google book API

{
  googleBookResultByISBN(isbn:"9780141365503") {
    totalItems,
    items {
      volumeInfo {
        title,
        authors,
        publisher,
        industryIdentifiers {
          identifier,
          type
        },
        pageCount,
        averageRating,
        imageLinks {
          smallThumbnail,
          thumbnail
        },
        language
      }
    }
  }
}

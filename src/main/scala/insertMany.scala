//import org.mongodb.scala._
//
//// To directly connect to the default server localhost on port 27017
//val mongoClient: MongoClient = MongoClient()
//// Use a Connection String
//// val mongoClient: MongoClient = MongoClient("mongodb://localhost")
//// or provide custom MongoClientSettings
//// val settings:MongoClientSettings = MongoClientSettings.builder()
////  .applyToClusterSettings(b => b.hosts(List(new ServerAddress("localhost")).asJava).
////  .build()
//
//// val mongoClient: MongoClient = MongoClient(settings)
//// val database: MongoDatabase = mongoClient.getDatabase("mydb")
//val collection: MongoCollection[Document] = database.getCollection("test");
//
//val doc: Document = Document("_id" -> 0, "name" -> "MongoDB", "type" -> "database",
//  "count" -> 1, "info" -> Document("x" -> 203, "y" -> 102))
//
//collection.insertOne(doc).results();
//
//val observable: Observable[Completed] = collection.insertOne(doc)
//
//// Explictly subscribe:
//observable.subscribe(new Observer[Completed] {
//
//  override def onNext(result: Completed): Unit = println("Inserted")
//
//  override def onError(e: Throwable): Unit = println("Failed")
//
//  override def onComplete(): Unit = println("Completed")
//})
//
//
//
//

namespace java by.maribo.web_service

struct Entity {
    1: i32 id,
    2: string name,
    3: string description,
}

struct Method {
    1: i32 id,
    2: string name,
    3: string description,
    4: string necessity,
}

service HandbookServer {
    list<Method> getAllMethods(),
    void addMethod(1:Method method),
    void deleteMethod(1:Method method),
    void modifyMethod(1:i32 id, 2:Method method),

    list<Entity> getAllEntities(1:string entityType),
    void addEntity(1:Entity entity, 2:string entityType),
    void deleteEntity(1:Entity entity, 2:string entityType),
    void modifyEntity(1:i32 id, 2:Entity entity, 3:string entityType),
}
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (1, 'public boolean equals(Object obj)', 'Метод сравнивает строку с указанным объектом. Результатом является значение true только в том случае, если аргумент не равен null и является строковым объектом (String), который представляет ту же последовательность символов как и этот объект.', 'обязателен');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (2, 'public int hashCode()', 'Метод используется для получения уникального целого номера для данного объекта. 
Когда необходимо сохранить объект как структуру данных в некой хэш-таблице, этот номер используется для определения его местонахождения в этой таблице. 
По умолчанию, метод hashCode() для объекта возвращает номер ячейки памяти, где объект сохраняется.', 'обязателен');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (3, 'public String toString()', 'Метод преобразует и возвращает строку.', 'обязателен');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (4, 'public <PropertyType> getPropertyName()', 'Метод возвращает значение выбранного свойства.', 'если присутствует соответствующее поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (5, 'public void setPropertyName (<PropertyType> value)', 'Метод изменяет значение выбранного свойства.', 'если присутствует соответствующее поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (6, 'public boolean isPropertyName()', 'Метод возвращает значение выбранного логического свойства.', 'если присутствует соответствующее поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (7, 'public <PropertyType> getPropertyName(int index)', 'Метод возвращает значение выбранного индексированного свойства.', 'если присутствует соответствующее индексированное поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (8, 'public void setPropertyName(int index, <PropertyType> value)', 'Метод изменяет значение выбранного индексированного свойства.', 'если присутствует соответствующее индексированное поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (9, 'public <PropertyType>[] getPropertyName()', 'Метод возвращает все значения выбранного массива свойств.', 'если присутствует соответствующее индексированное поле');
INSERT INTO java_bean.method (id, name, description, necessity) VALUES (10, 'public void setPropertyName(<PropertyType>[] value)', 'Метод изменяет все значения выбранного массива свойств.', 'если присутствует соответствующее индексированное поле');
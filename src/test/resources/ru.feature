# language: ru
@all
Функция: Аутентификация банковской карты
Банкомат должен спросить у пользователя PIN-код банковской карты
Банкомат должен выдать предупреждение, если пользователь ввел неправильный PIN-код
Аутентификация успешна, если пользователь ввел правильный PIN-код

Предыстория:
Допустим пользователь вставляет в банкомат банковскую карту
И банкомат выдает сообщение о необходимости ввода PIN-кода

@correct
Сценарий: Успешная аутентификация
Если пользователь вводит корректный PIN-код
То банкомат отображает меню и количество доступных денег на счету

@fail
Сценарий: Некорректная аутентификация
Если пользователь вводит некорректный PIN-код
То банкомат выдает сообщение, что введённый PIN-код неверный


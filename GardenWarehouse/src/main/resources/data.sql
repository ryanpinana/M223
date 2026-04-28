delete from item;
insert into item (id, code, type, name, price, item_count)
values (NEXT VALUE FOR item_seq, 'ala-01', 'Vaggies', 'Lattuce', 4.99, 10),
       (NEXT VALUE FOR item_seq, 'ala-02', 'Fruits', 'Strawbarry', 6.99, 100),
       (NEXT VALUE FOR item_seq, 'ala-03', 'Vaggies', 'Tomato', 5.99, 50);
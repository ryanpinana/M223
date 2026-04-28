delete from Post;
insert into Post (id, title, publishedDate, author, category, likes, content)
values (NEXT VALUE FOR post_seq, 'Esplosioni', '2025-01-03', 'Luc', 'News', 210000,'esplosione a Napoli'),
       (NEXT VALUE FOR post_seq, 'Estorsioni', '2026-02-12', 'Rian', 'News', 23,'truffe e furti fatte da cinesio'),
       (NEXT VALUE FOR post_seq, 'SON', '2026-02-01', 'Ridle', 'meme',1000,'el gato');
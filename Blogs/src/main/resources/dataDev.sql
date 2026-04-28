delete from Post;
insert into Post (id, title, publishedDate, author, category, likes, content)
values (NEXT VALUE FOR post_seq, 'Esplosioni', '2026-01-03', 'Luc', 'News', 2100,'esplosione alla scuola SAMT'),
       (NEXT VALUE FOR post_seq, 'Estorsioni', '2026-02-02', 'Rian', 'News', 2100,'truffe e furti ovunque in ticino'),
       (NEXT VALUE FOR post_seq, 'SON', '2026-03-01', 'Ridle', 'meme',1000000,'SON');
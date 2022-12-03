select count(*) from users;
select count(*) from books;

select count(*) from book_borrow
where is_returned=0;

select name from book_categories;


select name,isbn,author,description,year from books
where name='Agile Testing';

select * from users;

SELECT * FROM users WHERE 1=0;

select bc.name,count(*) from book_borrow bb
                                 inner  join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;

select count(*) as borrowedBooks from users u
inner join book_borrow b on u.id = b.user_id where is_returned = 0;

select id,status from users where id='6026';

select name, author,year from books where name='Chordeiles minor';

select name,isbn,author,description,year from books where name='Better Cleaner APIs v003';

select full_name,b.name,bb.borrowed_date from users u
                                                  inner join book_borrow bb on u.id = bb.user_id
                                                  inner join books b on bb.book_id = b.id
where full_name='Test Student 41'
order by 3 desc;


ALTER TABLE element
    ADD COLUMN search TSVECTOR GENERATED ALWAYS AS (
    setweight( to_tsvector('simple', "name"::TEXT), 'A')
    || ' '
    || setweight( to_tsvector('english', appearance::TEXT), 'D')
    || ' '
    || setweight( to_tsvector('english', category::TEXT), 'C')
    || ' '
    || setweight( to_tsvector('simple', discovered_by::TEXT), 'D')
    || ' '
    || setweight( to_tsvector('simple', named_by::TEXT), 'D')
    || ' '
    || setweight( to_tsvector('english', summary::TEXT), 'B') :: TSVECTOR
) STORED;
create index full_text_search_element on element using GIN (search);
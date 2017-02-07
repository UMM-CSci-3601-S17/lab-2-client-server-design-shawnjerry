

describe('testing basic functions', function(){



    it('should return the right link', function() {
        expect(getAllTodos()).toEqual("/api/todos");
    });

    it('should return the right link', function() {
        expect(sortByOwner()).toEqual("/api/todos?orderBy=owner");
    });

    it('should return the right link', function() {
        expect(sortByStatus()).toEqual("/api/todos?orderBy=status");
    });

    it('should return the right link', function() {
        expect(sortByCategory()).toEqual("/api/todos?orderBy=category");
    });



});


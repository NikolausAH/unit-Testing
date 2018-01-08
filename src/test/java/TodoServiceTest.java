import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.service.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
 //imp boongan    dibikin rep boongan biar yang kepanggil yang ini
    private TodoService todoService;

    @Before
    public void  setUp(){
//        this.todoRepository = new TodoRepository(); changed by @mock
        MockitoAnnotations.initMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }
    @After
    public void tearDown(){
        Mockito.verifyNoMoreInteractions(todoRepository);
    }

    @Test
    public void getAllTest() throws Exception{

//        todoRepository.store(new Todo("todo1",TodoPriority.MEDIUM));
        ArrayList<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("todo1",TodoPriority.MEDIUM));
        BDDMockito.given(todoRepository.getAll()).willReturn(todos);

        List<Todo> todoList = todoService.getAll();

        Assert.assertThat(todoList, Matchers.notNullValue());
        Assert.assertThat(todoList.isEmpty(),Matchers.equalTo(false));
        //verify
        BDDMockito.then(todoRepository).should().getAll();
//
//        Mockito.verifyNoMoreInteractions(todoRepository);
    }
    @Test
    public void saveTodoTest() throws Exception{
//        ArrayList<Todo> todos = new ArrayList<Todo>();
//        todos.add(new Todo("todo1",TodoPriority.MEDIUM));
//        BDDMockito.given(todoRepository.getAll()).willReturn(todos);
        boolean bool = todoService.saveTodo("todo3",TodoPriority.HIGH);
        Todo todo = new Todo("todo3", TodoPriority.HIGH);
        Assert.assertThat(bool, Matchers.equalTo(false));
        BDDMockito.then(todoRepository).should().store(todo);
//        BDDMockito.then(todoRepository).should().getAll();
//        Mockito.verifyNoMoreInteractions(todoRepository);
    }
}

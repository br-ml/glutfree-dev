package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import eu.glutfree.glutfree.model.service.ReceiptAddServiceModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.repository.ReceiptRepository;
import eu.glutfree.glutfree.repository.StoreRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import eu.glutfree.glutfree.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.UnicodeEscaper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReceiptServiceImplTest {

    private ReceiptEntity receiptEntity1, receiptEntity2;
    private UserEntity userEntity;

    private ReceiptServiceImpl receiptServiceImplToTest;

    @Mock
    ReceiptRepository mockedreceiptRepository;

    @Mock
     UserService mockeduserService;

    @Mock
    ModelMapper mockedmodelMapper;

    @Mock
    CloudinaryService mockedcloudinaryService;

    @BeforeEach
    public void init() {

        receiptEntity1 = new ReceiptEntity();
        receiptEntity1.setUrlToPic("testUrl");
        receiptEntity1.setUser(mockeduserService.findUserByUsername("admin"));
        receiptEntity1.setName("banitca");
        receiptEntity1.setDescription("mndobrabanitca");
        receiptEntity1.setDuration(2);
        receiptEntity1.setTypeOfMeal(TypeOfMealsEnums.ЗАКУСКА);



        receiptEntity2 = new ReceiptEntity();
        receiptEntity2.setUrlToPic("testUrl2");
        receiptEntity2.setUser(mockeduserService.findUserByUsername("user"));
        receiptEntity2.setName("kilichki");
        receiptEntity2.setDescription("mnogodobrikilichki");
        receiptEntity2.setDuration(5);
        receiptEntity2.setTypeOfMeal(TypeOfMealsEnums.ЗАКУСКА);

        receiptServiceImplToTest = new ReceiptServiceImpl(new ModelMapper(), mockedreceiptRepository, mockeduserService, mockedcloudinaryService );


    }


    @Test
    public void findAllStores_should_return_all_stores() {
        when(mockedreceiptRepository.findAll()).thenReturn(List.of(receiptEntity1,receiptEntity2 ));

        List<ReceiptViewModel> allReceipts = receiptServiceImplToTest.findAllReceipts();

        Assertions.assertEquals(2,allReceipts.size());

    }
//Raboti
    @Test
    public void deleteByid_should_delete_entity_by_id () {

        ReceiptEntity receiptEntity = new ReceiptEntity();

        Mockito.when(mockedreceiptRepository.findById(receiptEntity.getId())).thenReturn(Optional.of(receiptEntity));
        receiptServiceImplToTest.deleteReceipt(receiptEntity.getId());
        Mockito.verify(mockedreceiptRepository).delete(receiptEntity);
    }
// Ne ohste

    @Test
    public void addReceipt() throws IOException {

        UserEntity user1 = new UserEntity();

        ReceiptEntity receiptEntity3 = new ReceiptEntity();
        receiptEntity3.setUrlToPic("testUrl");
        receiptEntity3.setUser(user1);
        receiptEntity3.setName("banitca");
        receiptEntity3.setDescription("mndobrabanitca");
        receiptEntity3.setDuration(2);
        receiptEntity3.setTypeOfMeal(TypeOfMealsEnums.ЗАКУСКА);
        receiptEntity3.setProductsList("listazabanicata");

        mockedreceiptRepository.save(receiptEntity3);

        ReceiptAddServiceModel receiptAddServiceModel  = mockedmodelMapper.map(receiptEntity3, ReceiptAddServiceModel.class);
        receiptServiceImplToTest.addReceipt(receiptAddServiceModel);
        Mockito.verify(mockedreceiptRepository).save(receiptEntity3);

//        LessonEntity lesson=new LessonEntity();
//        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
//        lesson.setLessonName("lesson");
//        lesson.setLessonUrl("lessonUrl");
//
//        mockLessonRepository.save(lesson);
//        LessonServiceModel lessonServiceModel = mockModelMapper.map(lesson, LessonServiceModel.class);
//        lessonService.addLesson(lessonServiceModel);
//        Mockito.verify(mockLessonRepository).save(lesson);
    }




}

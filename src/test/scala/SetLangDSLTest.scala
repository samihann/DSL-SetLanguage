
import com.samihann.hw1.SetLangDSL.{MapMonadaicFunction, SetDeclarations, optimizationFunction}
import org.scalatest.funspec.AnyFunSpec
import com.samihann.hw1.SetLangDSL.SetDeclarations.*

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Set}

// Test File for the SetLangDSL function.
class SetLangDSLTest extends AnyFunSpec{

  /*******
   *
   * Homework 5 Test Cases
   *
   *
   */

  // Test Case 1
  describe("HW5: Partial Evaluation - 1") {
    it("should return partially evaluated union of sets when both sets are not defined") {
      assert(Union(Variable("partialSet1"), Variable("partialSet2")).eval() == Union(Variable("partialSet1"), Variable("partialSet2")))
    }
  }

  // Test Case 2
  describe("HW5: Partial Evaluation - 2") {
    it("should return partially evaluated union of sets when one of the sets are not defined") {
      Assign("partialSet3", Value(1), Value(2)).eval()
      assert(Union(Variable("partialSet4"), Variable("partialSet3")).eval() == Union(Variable("partialSet4"),Value(Set(1,2))))
    }
  }

  // Test Case 3
  describe("HW5: Optimize Union using standard optimization function. - 1") {
    it("should return correct union of sets when no optimization is possible") {
      Assign("UnSet1", Value(1), Value(2)).eval()
      Assign("UnSet2", Value(2), Value(3)).eval()
      assert(MapMonadaicFunction(Union(Variable("UnSet1"), Variable("UnSet2"))).map(optimizationFunction) == Set(1, 2, 3))
    }
  }

  // Test Case 4
  describe("HW5: Optimize Union using standard optimization function. - 2") {

    it("should optimized union of sets when both sets are similar") {
      Assign("opUnSet1", Value(1), Value(2)).eval()
      Assign("opUnSet2", Value(1), Value(2)).eval()
      assert(MapMonadaicFunction(Union(Variable("opUnSet1"), Variable("opUnSet2"))).map(optimizationFunction) == Set(1, 2))
    }
  }

  // Test Case 5
  describe("HW5: Optimize Union using standard optimization function. - 3") {

    it("should optimize union of sets when one set is empty") {
      Assign("opUnSet3", Value(1), Value(2)).eval()
      Assign("opUnSet4", Value(Set())).eval()
      assert(MapMonadaicFunction(Union(Variable("opUnSet3"), Variable("opUnSet4"))).map(optimizationFunction) == Set(1, 2))
    }
  }

  // Test Case 6
  describe("HW5: Optimize Intersection using standard optimization function. - 1") {
    it("should return correct intersect of sets when no optimization is possible") {
      Assign("InSet1", Value(1), Value(2)).eval()
      Assign("InSet2", Value(2), Value(3)).eval()
      assert(MapMonadaicFunction(Intersection(Variable("InSet1"), Variable("InSet2"))).map(optimizationFunction) == Set(2))
    }
  }

  // Test Case 7
  describe("HW5: Optimize Intersection using standard optimization function. - 2") {

    it("should optimize Intersection of sets when both sets are similar") {
      Assign("opInSet1", Value(1), Value(2)).eval()
      Assign("opInSet2", Value(1), Value(2)).eval()
      assert(MapMonadaicFunction(Intersection(Variable("opInSet1"), Variable("opInSet2"))).map(optimizationFunction) == Set(1, 2))
    }
  }

  // Test Case 8
  describe("HW5: Optimize Intersection using standard optimization function. - 3") {

    it("should optimize Intersection of sets when one set is empty") {
      Assign("opInSet3", Value(1), Value(2), Value(3)).eval()
      Assign("opInSet4", Value(mutable.HashSet())).eval()
      assert(MapMonadaicFunction(Intersection(Variable("opInSet3"), Variable("opInSet4"))).map(optimizationFunction) == Set())
    }
  }

  // Test Case 9
  describe("HW5: Optimize Difference using standard optimization function - 1") {
    it("should return correct set difference of sets when no optimization is possible") {
      Assign("dfSet1", Value(1), Value(2), Value(3)).eval()
      Assign("dfSet2", Value(2), Value(3), Value(4)).eval()
      assert(MapMonadaicFunction(Difference(Variable("dfSet1"), Variable("dfSet2"))).map(optimizationFunction) == Set(1))
    }
  }

  // Test Case 10
  describe("HW5: Optimize Difference using standard optimization function. - 2") {

    it("should optimize difference of sets when both sets are similar") {
      Assign("dfSet3", Value(1), Value(2), Value(3)).eval()
      Assign("dfSet4", Value(1), Value(2), Value(3)).eval()
      assert(MapMonadaicFunction(Difference(Variable("dfSet3"), Variable("dfSet4"))).map(optimizationFunction) == Set())
    }

  }

  // Test Case 11
  describe("HW5: Optimize Difference using standard optimization function - 3") {

    it("should optimize difference of sets when second set is empty") {
      Assign("dfSet6", Value(1), Value(2), Value(3)).eval()
      Assign("dfSet5", Value(Set())).eval()
      assert(MapMonadaicFunction(Difference(Variable("dfSet6"), Variable("dfSet5"))).map(optimizationFunction) == Set(1, 2, 3))
    }

  }

  // Test Case 12
  describe("HW5: Optimize Difference using standard optimization function - 4") {

    it("should optimize difference of sets when first set is empty") {
      Assign("dfSet8", Value(1), Value(2), Value(3)).eval()
      Assign("dfSet7", Value(Set())).eval()
      assert(MapMonadaicFunction(Difference(Variable("dfSet7"), Variable("dfSet8"))).map(optimizationFunction) == Set())
    }

  }

  // Test Case 13
  describe("HW5: Optimize Symmetric Difference using standard optimization function. - 1") {

    it("should return correct set Symmetric difference of sets when no optimization is possible") {
      Assign("sdfSet1", Value(1), Value(2), Value(3)).eval()
      Assign("sdfSet2", Value(2), Value(3), Value(4)).eval()
      assert(MapMonadaicFunction(Difference(Variable("sdfSet1"), Variable("sdfSet2"))).map(optimizationFunction) == Set(1))
    }
  }

  // Test Case 14
  describe("HW5: Optimize Symmetric Difference using standard optimization function. - 2") {

    it("should optimize Symmetric difference of sets when both sets are similar") {
      Assign("sdfSet3", Value(1), Value(2), Value(3)).eval()
      Assign("sdfSet4", Value(1), Value(2), Value(3)).eval()
      assert(MapMonadaicFunction(SymmetricDifference(Variable("sdfSet3"), Variable("sdfSet4"))).map(optimizationFunction) == Set())
    }

  }

  // Test Case 15
  describe("HW5: Optimize Symmetric Difference using standard optimization function. - 3") {

    it("should optimize Symmetric difference of sets when second set is empty") {
      Assign("sdfSet6", Value(1), Value(2), Value(3)).eval()
      Assign("sdfSet5", Value(Set())).eval()
      assert(MapMonadaicFunction(Difference(Variable("sdfSet6"), Variable("sdfSet5"))).map(optimizationFunction) == Set(1, 2, 3))
    }
  }


  /***
   * HOMEWORK 4 TEST CASES
   *
   *
   *
   *
   */

  // Test Case 1
  describe("HW4 - IfConditionalStatement condition - thenClause execution") {
    it("Condition Returns true, Then operations should be executed") {
      Assign("ifTestSetOne", Value(1), Value(2), Value(3)).eval()
      IfConditionalStatement(ConditionalCheck(Variable("ifTestSetOne"), Value(2)),
        Then(Insert(Variable("ifTestSetOne"), Value(6))),
        Else(Insert(Variable("ifTestSetOne"), Value(5)))
      ).eval()
      val result = Variable("ifTestSetOne").eval()
      assert(result == scala.collection.mutable.Set(1,2,3,6))

    }
  }

  // Test case 2
  describe("HW4 - IfConditionalStatement condition - elseClause execution") {
    it("Condition Returns false, ELse operations should be executed") {
      Assign("ifTestSetTwo", Value(1), Value(2), Value(3)).eval()
      IfConditionalStatement(ConditionalCheck(Variable("ifTestSetTwo"), Value(5)),
        Then(Delete(Variable("ifTestSetTwo"), Value(2))),
        Else(Insert(Variable("ifTestSetTwo"), Value(5)))
      ).eval()
      val result = Variable("ifTestSetTwo").eval()
      assert(result == scala.collection.mutable.Set(1,2,3,5))
    }
  }

  // Test case 3
  describe("HW4 - CatchException: Exceptions Caught") {
    it("Exception should be raised and last statement should not be executed") {

      ExceptionClassDef("ExceptionOne", Field("Reason")).eval()
      val errorFound = CatchException("ExceptionOne",
        Assign("exceptionSet1", Value(1), Value(2), Value(3)),
        // Throw an error if data is not present.
        ThrowException("ExceptionOne", Assign("Reason", Value("The data is not present in Set"))),
        Delete(Variable("exceptionSet1"), Value(4)),
      ).eval()
      assert(errorFound == "EXCEPTION RAISED: The data is not present in Set")
    }
  }

  // Test case 4
  describe("HW4 - CatchException: Exceptions Caught") {
    it("Exception should be raised and catch block should be executed") {

      ExceptionClassDef("ExceptionTwo", Field("Reason")).eval()
      Assign("exceptionSet2", Value(1), Value(2), Value(3)).eval()
      val errorFound2 = CatchException("ExceptionTwo",
        // Throw an error if data is not present.
        ThrowException("ExceptionTwo", Assign("Reason", Value("Data is not present."))),
        Delete(Variable("exceptionSet2"), Value(4)),
        // Catch
        Catch(Variable("ExceptionTwo"), Insert(Variable("exceptionSet2"), Value(100)))
      ).eval()
      assert(errorFound2 == "EXCEPTION RAISED: Data is not present.")
      assert(Variable("exceptionSet2").eval() == scala.collection.mutable.Set(1,2,3,100))
    }
  }

  // Test case 5
  describe("HW4 - CatchException: Exceptions Caught") {
    it("Exception should be raised according to if else block") {

      ExceptionClassDef("ExceptionThree", Field("Reason")).eval()
      val errorFound3 = CatchException("ExceptionThree",
        Assign("exceptionSet3", Value(1), Value(2), Value(3)),
        IfConditionalStatement(ConditionalCheck(Variable("exceptionSet3"), Value(5)),
          Then(Delete(Variable("exceptionSet3"), Value(2))),
          Else(ThrowException("ExceptionThree", Assign("Reason",Value("Value 5 not present"))))
        ),
        Insert(Variable("exceptionSet3"), Value(6)),
        Catch(Variable("ExceptionThree"), Insert(Variable("exceptionSet3"), Value(5)))
      ).eval()
      assert(errorFound3 == "EXCEPTION RAISED: Value 5 not present")
      assert(Variable("exceptionSet3").eval() == scala.collection.mutable.Set(1,2,3,5))

    }
  }

  // Test Case 6
  describe("HW4 - ThrowException: Update the field for class") {
    it("should update the Reason field for the exception class successfully") {
      ExceptionClassDef("ExceptionFour", Field("Reason")).eval()
      val result = ThrowException("ExceptionFour", Assign("Reason",Value("THis is a Test exception"))).eval()
      assert(result == "THis is a Test exception" )
    }
  }

  // Test Case 7
  describe("HW4 - ConditionalCheck validation ") {
    it("should return true") {
      Assign("checkSet1", Value(1), Value(2), Value(3)).eval()
      val newResult =  ConditionalCheck(Variable("checkSet1"), Value(2)).eval()
      assert(newResult == true )
    }
  }

  // Test Case 8
  describe("HW4 - ConditionalCheck validation ") {
    it("should return false") {
      Assign("checkSet2", Value(1), Value(2), Value(3)).eval()
      val newResult =  ConditionalCheck(Variable("checkSet2"), Value(5)).eval()
      assert(newResult == false )
    }
  }












  /***
   * HOMEWORK 3 TEST CASES
   *
   *
   */

  // Test Case 1
  describe("HW3 - Abstract Class definition") {
    it("should create Abstract class definition and return true.") {
      val test = AbstractClassDef("class1", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("method1", ListBuffer("p1", "p2")))).eval()
      assert(test === true)
    }
  }

  // Test Case 2
  describe("HW3 - Abstract Class definition error when no abstract method is declared.") {
    it("should return error when no abstract function is declared in class definition") {
      val test = AbstractClassDef("class2", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 3
  describe("HW3 - Class Extends Abstract Class") {
    it("should return error when all abstract class are not overridden") {
      AbstractClassDef("abstractClass", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      val test = ClassDef("extendedClass", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("newMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Extends "abstractClass"
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 4
  describe("HW3 - Class Extends Abstract Class") {
    it("should return true when extenededClass is created") {
      AbstractClassDef("abstractClass1", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      val test = ClassDef("extendedClass1", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Extends "abstractClass1"
      assert(test === true)
    }
  }

  // Test Case 5
  describe("HW3 - New Object gives error when created for Abstract Class") {
    it("should return error when object is created for Abstract Class") {
      AbstractClassDef("abstractClass2", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      val test = NewObject("object1", "abstractClass2").eval()
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 6
  describe("HW3 - New Object return true when created for inherited Class Def") {
    it("should return true when object is created for Abstract Class") {
      AbstractClassDef("abstractClass3", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      ClassDef("extendedClass2", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Extends "abstractClass3"
      val test = NewObject("object2", "extendedClass2").eval()
      assert(test === true)
    }
  }

  // Test Case 7
  describe("HW3 - Invoked method on inherited class should refer to Virtual Dispatch Table for Lookup for Overwritten Function") {
    it("should return output when invoked method on inherited class should refer to Virtual Dispatch Table for Lookup for Overwritten Function") {
      AbstractClassDef("abstractClass3", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2")))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      ClassDef("extendedClass2", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Extends "abstractClass3"
      NewObject("object3", "extendedClass2").eval()
      val test = InvokeMethod("object3", "abstractMethod", Assign("a", Value(2), Value(3)), Assign("b", Value(4), Value(5))).eval()
      assert(test === scala.collection.mutable.Set(2,3,4,5))
    }
  }

  // Test Case 8
  describe("HW3 - Interface Declaration") {
    it("should return true when interface definition is given") {
      val test = InterfaceDecl("interface1", Public(Field("parentField")), Constructor(Assign("parentField", Value(2))), Public(AbstractMethod("abstractMethod",ListBuffer("a", "b")))).eval()
      assert(test === true)
    }
  }

  // Test Case 9
  describe("HW3 - Interface gives error when Method is declared instead of Abstract Method.") {
    it("should return error when Method is declared instead of Abstract Method in interface definition") {
      val test = InterfaceDecl("interface2", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 10
  describe("HW3 - New Object Creation returns error for Interface") {
    it("should return error when new object is created for Interface") {
      InterfaceDecl("interface4", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(AbstractMethod("method1", ListBuffer("p1", "p2")))).eval()
      val test = NewObject("interfaceObject", "interface4").eval()
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 11
  describe("HW3 - Class Implements Interface gives error when not all abstract methods are overwritten") {
    it("should return error when all abstract methods are not overwritten") {
      InterfaceDecl("parentInterface", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      val test = ClassDef("implementedClass", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("newMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Implements "parentInterface"
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case 12
  describe("HW3 - Class Implements Interface returns true") {
    it("should return true when all abstract methods are overwritten and class definition is added to scope") {
      InterfaceDecl("parentInterface1", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      val test = ClassDef("implementedClass1", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Implements "parentInterface1"
      assert(test === true)
    }
  }

  // Test Case 13
  describe("HW3 - New Object return true when created for implemented from a interface") {
    it("should return true when object is created for Abstract Class") {
      InterfaceDecl("parentInterface2", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      ClassDef("implementedClass2", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Implements "parentInterface2"
      val test = NewObject("implementedObject1", "implementedClass2").eval()
      assert(test === true)
    }
  }

  // Test Case 14
  describe("HW3 - Invoked method on implemented class should refer to Virtual Dispatch Table for Lookup for Overwritten Function") {
    it("should return output when invoked method on implemented class should refer to Virtual Dispatch Table for Lookup for Overwritten Function") {
      InterfaceDecl("parentInterface3", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(AbstractMethod("abstractMethod", ListBuffer("p1", "p2")))).eval()
      ClassDef("implementedClass3", Public(Field("field1")), Constructor(Assign("field1", Value(2))), Public(Method("abstractMethod",ListBuffer("a", "b"), Union(Variable("a"), Variable("b"))))) Implements "parentInterface3"
      NewObject("implementedObject3", "implementedClass3").eval()
      val test = InvokeMethod("implementedObject3", "abstractMethod", Assign("a", Value(2), Value(3)), Assign("b", Value(4), Value(5))).eval()
      assert(test === scala.collection.mutable.Set(2,3,4,5))
    }
  }


// ************************ End oF HW3 Test Cases **********************************




  /***
   * Homework Two Test Cases.
   *
   *
   */

  // Test Case No. One
  // THe class should be created and on successful creation, it will return true.
  describe("HW2 - Class definition") {
    it("should create class definition and return true.") {
      val test = ClassDef("class3", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      assert(test === true)
    }
  }

  // Test Cases No. Two
  describe("HW2 -  Class definition") {
    it("should not allow creation of two classes with same name.") {
      ClassDef("class4", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      val test = ClassDef("class4", Public(Field("field2")), Public(Method("method4", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      assert(test === "!!!Error!!!")
    }
  }

  // Test Case No. Three
  describe("HW2 - Class definition and create new object and constructor") {
    it("should return correct evaluation of constructor assign operation after creating new object") {
      ClassDef("class5", Public(Field("field1")), Constructor(Assign("field1", Value(1))), Public(Method("method1", ListBuffer("p1", "p2"), Union(Variable("p1"), Variable("p2"))))).eval()
      NewObject("object1", "class5").eval()
      assert(RetrieveField("object1", "field1").eval() === 1)
    }
  }


  // Test Case No. Four
  describe("HW2 - Invoke method") {
    it("should return correct evaluation of method with correct parameters") {
      ClassDef("class6", Public(Field("field2")), Constructor(Assign("field2", Value(1))), Public(Method("method2", ListBuffer("p1", "p2"), Union(Variable("p3"), Variable("p4"))))).eval()
      NewObject("newObject12", "class6").eval()
      val result = InvokeMethod("newObject12", "method2", Assign("p3", Value(1), Value(2), Value(3)), Assign("p4", Value(1), Value(4))).eval()
      assert(result == scala.collection.mutable.Set(1, 2, 3, 4))
    }
  }

  // Test Case No. Five
  describe("HW2 - Inheritance Test One") {
    it("child should be able to take parent's protected fields") {
      ClassDef("parentClass2", Protected(Field("parentField2")), Constructor(Assign("parentField2", Value(1)))).eval()
      ClassDef("childClass2", Public(Field("childField2")), Constructor(Assign("childField2", Value(2)))) Extends "parentClass2"
      NewObject("childObject2", "childClass2").eval()
      assert(RetrieveField("childObject2", "parentField2").eval() == null)
    }
  }

  // Test Case No. Six
  describe("HW2 - Inheritance Test Two") {
    it("child should return its own field correctly") {
      ClassDef("parentClass", Public(Field("parentField")), Constructor(Assign("parentField", Value(1)))).eval()
      ClassDef("childClass", Public(Field("childField")), Constructor(Assign("childField", Value(2)))) Extends "parentClass"
      NewObject("childObject", "childClass").eval()
      assert(RetrieveField("childObject", "childField").eval() === 2)
    }
  }

  // Test Case No. Seven
  describe("HW2 - Inheritance Test Three") {
    it("child should contain its parents fields with null value") {
      ClassDef("parentClass1", Public(Field("parentField1")), Constructor(Assign("parentField1", Value(1)))).eval()
      ClassDef("childClass1", Public(Field("childField1")), Constructor(Assign("childField1", Value(2)))) Extends "parentClass1"
      NewObject("childObject1", "childClass1").eval()
      assert(RetrieveField("childObject1", "parentField1").eval() == null)
    }
  }


  /***
   * Homework One Test Cases.
   */

  // Test Case No. One
  describe("Test Value"){
    it("should eval()ute DSL return with the correct value.") {
      assert(Value(100).eval() == 100)
    }
  }

  // Test Case No. Two
  describe("Test Assign & Variable function"){
    it("should bind the values correctly and retrieve them") {
      Assign("testVar", Value(100)).eval()
      assert(Variable("testVar").eval() == 100)
    }
  }

  // Test Case No. Three
  describe("Test Insert function"){
    it("should bind values in the map and update the set with additonal value") {
      Assign("testSet1", Value(100),Value(101)).eval()
      Insert(Variable("testSet1"), Value(102)).eval()
      assert(Variable("testSet1").eval() == scala.collection.mutable.Set(100,101,102))
    }
  }

  // Test Case No. Five
  describe("Test Delete function") {
    it("should delete an element from the set") {
      Assign("testSet2", Value(100),Value(101),Value(102)).eval()
      Delete(Variable("testSet2"), Value(100)).eval()
      assert(Variable("testSet2").eval() == scala.collection.mutable.Set(101, 102))
    }
  }

  // Test Case No. Six
  describe("Test Union Operation") {
    it("should return correct union of sets") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(5),Value(6)).eval()
      assert(Union(Variable("testSet3"), Variable("testSet4")).eval() == scala.collection.mutable.Set(1, 2, 3, 5, 6))
    }
  }

  // Test Case No. Seven
  describe("Test Intersection Operation") {
    it("should return correct intersect of sets") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(3),Value(6)).eval()
      assert(Intersection(Variable("testSet3"), Variable("testSet4")).eval() == scala.collection.mutable.Set(3))
    }
  }

  // Test Case Eight
  describe("Test Difference Operation") {
    it("should return correct set difference of sets") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(3),Value(6)).eval()
      assert(Difference(Variable("testSet3"), Variable("testSet4")).eval() == scala.collection.mutable.Set(1, 2))
    }
  }

  // Test Case Nine
  describe("Test Symmetric Difference Operation") {
    it("should return correct set symmetric difference of sets") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(3),Value(6)).eval()
      assert(SymmetricDifference(Variable("testSet3"), Variable("testSet4")).eval() == scala.collection.mutable.Set(1, 2, 6))
    }
  }


  // Test Case Ten
  describe("Test Cartesian Product Operation") {
    it("should return correct set cartesian product of sets") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(5),Value(6)).eval()
      assert(CartesianProduct(Variable("testSet3"), Variable("testSet4")).eval() == scala.collection.mutable.Set((1,6), (2,5), (3,6), (3,5), (2,6), (1,5)))
    }
  }

  // Test Case ELeven
  describe("Test Macro Function") {
    it("should assign and eval() macro correctly") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      Assign("testSet4", Value(5),Value(6)).eval()
      Macro("macro", Union(Variable("testSet3"), Variable("testSet4"))).eval()
      assert(ImpMacro(Variable("macro")).eval() == scala.collection.mutable.Set(1, 2, 3, 5, 6))
    }
  }

   // Test Case 12
  describe("Test Check Function") {
    it("should check if elements are present in a set") {
      Assign("testSet3", Value(1), Value(2),Value(3)).eval()
      assert(Check(Variable("testSet3"), Value(1)).eval() == true)
    }
    it("should check if elements are absent in a set") {
      Assign("testSet4", Value(5),Value(6)).eval()
      assert(Check(Variable("testSet4"), Value(2)).eval() == false)
    }
  }

  // Test Case No. 13
  describe("Test Scope Function") {
    it("should create a scope to for the operations inside it") {
      val result = Scope("scope1", Assign("a2",Value(100),Value(3)), Assign("a1",Value(3),Value(4)), Union(Variable("a1"),Variable("a2"))).eval()
      assert(result == scala.collection.mutable.Set(100,3,4))
    }
    it("should check an anonymous Scope") {
      val result = Scope("", Assign("a2",Value(100),Value(3)), Assign("a1",Value(3),Value(4)), Difference(Variable("a1"),Variable("a2")) ).eval()
      assert(result == scala.collection.mutable.Set(4))
    }
  }

  // Test Case No. 14
  describe("Test GetScopeVariable Function") {
    it("should retrieve a variable to for the operations inside it") {
      Scope("scope1", Assign("innerVariable",Value(100))).eval()
      assert(GetScopeVariable("scope1","innerVariable").eval() == 100)
    }
  }


}
package com.company.Subject;

import java.util.ArrayList;

public class Subject implements Element {
    private String name;
    private String description;

    public Subject(){}

    public Subject(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setSubject(String name, String description, ArrayList<Subject> subjectList){
        Subject subject = new Subject(name, description);
        subjectList.add(subject);
    }

    public void removeSubject(String subject, ArrayList<Subject> subjectList){
        subjectList.removeIf(
                s -> s.getName().equals(subject)
                        || s.getDescription().equals(subject));
    }

    public void removeSubject(Subject subject, ArrayList<Subject> subjectList){
        subjectList.remove(subject);
    }

    public Subject getSubject(String subjectName, ArrayList<Subject> subjectList){
        if(subjectList == null){
            return null;
        }
        for(Subject s : subjectList){
            if(s!=null && s.getName().equals(subjectName)){
                return s;
            }
        }
        return null;
    }

    public Element getElement(String elementName, ArrayList<Element> elementList){
        if(elementList == null){
            return null;
        }
        for(Element e : elementList){
            if(e!=null && e.getName().equals(elementName)){
                return e;
            }
        }
        return null;
    }
}

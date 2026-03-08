const slides = document.querySelector(".slides");
const images = document.querySelectorAll(".slides img");
const dots = document.querySelectorAll(".dot");

let index = 0;
let interval;

function updateSlider(){

    slides.style.transform = `translateX(-${index * 100}%)`;

    dots.forEach(dot => dot.classList.remove("active"));
    dots[index].classList.add("active");

}

function startSlide(){
    interval = setInterval(() => {

        index++;

        if(index >= images.length){
            index = 0;
        }

        updateSlider();

    },4000);
}

dots.forEach((dot,i)=>{

    dot.addEventListener("click",()=>{

        index = i;
        updateSlider();

    });

});

const slider = document.querySelector(".slider");

slider.addEventListener("mouseenter",()=>{
    clearInterval(interval);
});

slider.addEventListener("mouseleave",()=>{
    startSlide();
});

startSlide();
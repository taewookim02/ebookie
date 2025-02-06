<template>
    <JournalWrapper>
        <h1>Community</h1>
        <section class="community">
            <CommunityCard heading-text="HEY" paragraph-text="HELO" />
            <CommunityCard heading-text="HEY" paragraph-text="HELO" />
            <CommunityCard heading-text="HEY" paragraph-text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras nec vulputate massa, sed efficitur dolor. Integer id est et mi pharetra pretium sit amet sed est. Morbi egestas tortor ut tempor volutpat. Vivamus quis sem egestas, fringilla ex vel, tempus libero. Donec fermentum ante nec turpis pulvinar tempor. Morbi blandit, nisi a tempus scelerisque, dolor arcu tempor nibh, eu malesuada est justo pharetra erat. Donec nibh nisl, consequat id dapibus vel, varius non mauris.

Donec vitae mauris est. Donec dignissim dapibus metus, non convallis erat ornare sit amet. Proin vel diam hendrerit augue condimentum facilisis. Maecenas efficitur metus tristique, congue enim in, fermentum nunc. Maecenas lacinia pretium tortor, at luctus dui tristique sed. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque tincidunt, ipsum eu dignissim mattis, velit turpis semper elit, eget facilisis mi eros a massa. Morbi metus enim, tempor nec erat quis, egestas tempor justo. Fusce gravida lobortis auctor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse fringilla lorem in ultricies finibus. Donec ornare porta metus, id fringilla lorem lacinia non. Aliquam in finibus odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aliquam dui lacus, lobortis eget lacinia non, rutrum id mauris. Mauris accumsan, diam nec cursus ultricies, erat nisl luctus lectus, eu efficitur purus arcu ac sapien.

Suspendisse potenti. Cras nec dolor viverra enim maximus porttitor. Morbi eu neque tincidunt, tristique purus ut, posuere justo. Vivamus neque odio, sollicitudin id tristique ut, porttitor at nulla. Duis dignissim aliquam turpis, a finibus ligula auctor sed. Donec in vestibulum tellus, eget facilisis quam. Donec at nunc elementum, blandit ligula eget, posuere risus. Nullam sit amet convallis ante. Aliquam dictum facilisis leo a maximus. Vivamus ut ullamcorper velit, eu gravida odio. Nunc vel tincidunt magna, id viverra sapien. Vivamus tristique nulla at accumsan ullamcorper.

Phasellus nec sollicitudin nulla. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam finibus lorem erat. Sed iaculis pellentesque auctor. Donec a malesuada ante, bibendum tristique nunc. Donec vestibulum est ac suscipit aliquam. Nullam pharetra justo dolor. Morbi mollis risus id turpis aliquet varius. Fusce bibendum mauris sed vulputate interdum.

Maecenas nec cursus est, id molestie est. Nam venenatis sapien id massa convallis scelerisque. Proin vitae ante vel lectus ullamcorper condimentum in at risus. Maecenas tincidunt turpis a luctus laoreet. Suspendisse ac massa nisi. Donec euismod, risus et aliquam volutpat, massa urna venenatis felis, at interdum ex arcu id lectus. Suspendisse egestas massa ornare lacinia facilisis. Donec ut ipsum ut risus vehicula tempus.

" />
            <CommunityCard heading-text="HEY" paragraph-text="HELO" />

        </section>
    </JournalWrapper>
</template>
<script setup>
import CommunityCard from '@/components/shared/CommunityCard.vue';
import JournalWrapper from '@/components/shared/JournalWrapper.vue';

</script>
<style scoped>
body {
    padding: 0;
}

.container {
    display: grid;
    grid-template-columns: min-content 1fr;
    max-height: 100vh;
}

.main {
    padding-left: 8rem;
    padding-right: 8rem;
    display: flex;
    flex-direction: column;
    gap: 4rem;
}

.community {
    /* display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 1rem;
    align-items: start; */
    /* TODO: 2 columns or ... height 290px, 170px  */
    display: flex;
    flex-wrap: wrap;
    gap: 1.6rem;
}

@media (max-width: 600px) {
    .community {
        grid-template-columns: 1fr;
    }
}
</style>
<template>
    <nav class="nav">
        <!-- logo -->
         <div class="nav_upper">
            written thoughts
         </div>

        <!-- middle -->
        <div class="nav__middle">
            <NavItem link="/community" label="community" :icon="PhUsersThree" />
            <NavItem link="/journal" label="journal" :icon="PhPenNib" />
            <NavItem link="/journal/list" label="list" :icon="PhBookOpenText" />
        </div>

        <!-- The below should be in a dropdown -->
        <div class="nav__lower">
            <router-link to="/user/edit" class="nav__item">
                <PhGearSix :size="16" />
                <p>User settings</p>
            </router-link>
            <router-link to="/journal/download" class="nav__item">
                <PhDownloadSimple :size="16" />
                <p>download</p>
            </router-link>
            <a href="https://www.google.com" rel="noopener noreferrer" target="_blank" class="nav__item">
                <!-- TODO: tooltip + link to coffee? -->
                <PhCoffee :size="16" />
                <p>buy me a coffee</p>
            </a>
        </div>
    </nav>
</template>

<script setup>
import { PhUsersThree, PhBookOpenText, PhGearSix, PhDownloadSimple, PhCoffee, PhPenNib } from '@phosphor-icons/vue';
import NavItem from '../shared/NavItem.vue';
</script>

<style scoped>
.nav {
    padding: 16px 8px;
    min-width: 8rem;
    max-height: 100vh;
    background-color: antiquewhite;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.nav__middle {
    display: flex;
    flex-direction: column;
    gap: 24px;
}

</style>
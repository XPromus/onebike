<script lang="ts">
	import Icon from "@iconify/svelte";
	import Button from "../Button.svelte";
	import type { RiderEditDto, RiderWithIds } from "$lib/types/client/rider.types";
	import type { GetNationResponse } from "$lib/types/server/nation.types";
	import type { GetTeamResponse } from "$lib/types/server/team.types";

    let {
        selectedRider,
        nations,
        teams,
    }: {
        selectedRider: RiderWithIds | null,
        nations: GetNationResponse,
        teams: GetTeamResponse,
    } = $props();

    let selectedNation = $state<number>();
    let selectedTeam = $state<number>();

    let submitError = $state<string | null>(null);
    let rider = $derived<RiderEditDto>({
        id: selectedRider != undefined ? selectedRider.id : undefined,
        firstName: selectedRider != undefined ? selectedRider.firstName : "",
        lastName: selectedRider != undefined ? selectedRider.lastName : "",
        dateOfBirth: selectedRider != undefined ? new Date(selectedRider.dateOfBirth) : new Date(),
        nationId: selectedRider != undefined ? selectedRider.nationId : 1,
        teamId: selectedRider != undefined ? selectedRider.teamId : undefined,
    })
</script>

<div class="flex flex-col space-y-2">
    <div class="flex flex-row space-x-2">
        <div class="flex flex-row flex-1 space-x-2 items-center">
            <span class="text-center">First Name</span>
            <input class="px-2 py-1 rounded-md form-input grow" type="text" />
        </div>
        <div class="flex flex-row flex-1 space-x-2 items-center">
            <span class="text-center">Last Name</span>
            <input class="px-2 py-1 rounded-md form-input grow" type="text" />
        </div>
    </div>
    <div class="flex flex-row space-x-2 items-center">
        <span class="text-center basis-1/5">Birthday</span>
        <input class="px-2 py-1 rounded-md form-input grow" type="date"/>
    </div>
    <div class="flex flex-row space-x-2 items-center">
        <span class="text-center basis-1/5">Nationality</span>
        <select class="grow form-select rounded-md" bind:value={selectedNation} placeholder="Nationality">
            {#each nations as nation }
                <option value={nation.id}>
                    {nation.longName} {nation.flagEmoji}
                </option>
            {/each}
        </select>
        <Button onclick={() => {selectedNation = 0}} class="rounded-md" size="sm">
            <Icon icon="material-symbols:clear-all-rounded" />
        </Button>
    </div>
    <div class="flex flex-row space-x-2 items-center">
        <span class="text-center basis-1/5">Team</span>
        <select class="grow form-select rounded-md" bind:value={selectedTeam} placeholder="Team">
            {#each teams as team }
                <option value={team.id}>
                    {team.teamName}
                </option>
            {/each}
        </select>
        <Button onclick={() => {selectedTeam = 0}} class="rounded-md" size="sm">
            <Icon icon="material-symbols:clear-all-rounded" />
        </Button>
    </div>
</div>
